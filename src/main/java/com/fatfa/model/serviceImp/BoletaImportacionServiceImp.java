package com.fatfa.model.serviceImp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fatfa.exceptions.ErrorConflictException;
import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.entity.BoletasImportacionModel;
import com.fatfa.model.entity.EstadoPagoModel;
import com.fatfa.model.entity.UsuarioModel;
import com.fatfa.model.repository.IBoletaImportacionRepository;
import com.fatfa.model.repository.IBoletaRepository;
import com.fatfa.model.service.IBoletaImportacionService;
import com.fatfa.model.service.IFilesStorageService;
import com.fatfa.model.service.IUsersService;
import com.fatfa.utils.Constantes;

@Service
public class BoletaImportacionServiceImp implements IBoletaImportacionService {

	private static final Logger log = LoggerFactory.getLogger(BoletaImportacionServiceImp.class);

	@Autowired
	private IBoletaImportacionRepository repoBoletaImport;

	@Autowired
	private IBoletaRepository repoBoleta;

	@Autowired
	private IFilesStorageService srvStorage;

	@Autowired
	private IUsersService srvUser;

	@Override
	public HashMap<String, Object> srvImportarBoletaPagoFacil(MultipartFile archivo) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			String nameFile = archivo.getOriginalFilename();
			srvStorage.save(archivo, "temp");

			File comprobanteBoleta = new File("src//main//webapp//temp//" + nameFile);
			BufferedReader readFile = new BufferedReader(new FileReader(comprobanteBoleta));

			String cabeceraFile = "";
			String cabeceraLoteFile = "";
			String fechaProcesadaFile = "";
			List<String> detalleFile = new ArrayList<>();
			String probaleCodigoBarras = "";

			BoletaModel boletaBuscar = null;
			String strng;
			while ((strng = readFile.readLine()) != null) {
				// System.out.println(strng);
//				# CAPTURAR CABECERA PAGO FACIL
				if (strng.substring(9, 78).compareTo(Constantes.ORIGNE_NAME_PAGO_FACIL) == 0) {
					cabeceraFile = strng;
					fechaProcesadaFile = strng.substring(1, 9);
				}
//				# CAPTURAR SUB CABECERA PAGO FACIL
				else if (strng.substring(0, 9).compareTo("3" + fechaProcesadaFile) == 0) {
					cabeceraLoteFile = strng;
				}
//				# CAPTURAR DETALLE 
				else if (strng.substring(0, 1).compareTo("5") == 0 || strng.substring(0, 1).compareTo("6") == 0
						|| strng.substring(0, 1).compareTo("7") == 0) {
					if (strng.substring(0, 1).compareTo("6") == 0) {
						probaleCodigoBarras = strng.substring(1, 81).trim();

						if (boletaBuscar == null) {
							boletaBuscar = repoBoleta.findByCodigoBarrasAndEstadoPagoIdEstadoPago(probaleCodigoBarras,
									1);

						}

					}
				}
				detalleFile.add(strng);
			}
			readFile.close();
			comprobanteBoleta.delete();

			if (boletaBuscar != null) {
				String fechaPago = fechaProcesadaFile.substring(0, 4) + "-" + fechaProcesadaFile.substring(4, 6) + "-"
						+ fechaProcesadaFile.substring(6, 8);
				BoletasImportacionModel importarData = new BoletasImportacionModel();
				importarData.setFechaImportacion(new Date());
				importarData.setFechaPago(Constantes.utilConvertirFecha(fechaPago, "yyy-MM-dd"));
				importarData.setImporteCobrado(boletaBuscar.getImporteTotal());
				importarData.setNombreArchivoimportacion(nameFile);
				importarData.setBoleta(boletaBuscar);
				importarData.setMedioPago(boletaBuscar.getBanco());
				importarData.setContenidoFile(detalleFile.toString());

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null) {
					UsuarioModel userLogin = srvUser.srvBuscarUsuarioEmail(auth.getPrincipal().toString());
					importarData.setIdUserLogin(userLogin.getIdUsuario());
					repoBoletaImport.save(importarData);
					boletaBuscar.setEstadoPago(new EstadoPagoModel(2));
					repoBoleta.save(boletaBuscar);

				} else {
					throw new ErrorConflictException("No se pudo obtener respuesta de tu servidor :(");
				}
			} else {
				throw new ErrorConflictException("No se encontro ninguna Boleta con codigo de barras <b>"
						+ probaleCodigoBarras
						+ "<b>, el archivo de importacion es posible que este incompleto ó ya fue importado anteriormente.");
			}

			response.put("message", "El archivo fue importado correctamente.");
			response.put("cabecera", cabeceraFile);
			response.put("subCabecera", cabeceraLoteFile);

		} catch (FileNotFoundException file) {
			log.error("ERROR IMPORTACION BOLETA => " + file.toString());
		} catch (IOException io) {
			log.error("ERROR IMPORTACION BOLETA => " + io.toString());
		} catch (ParseException pas) {
			log.error("ERROR IMPORTACION BOLETA => " + pas.toString());
		} catch (Exception e) {
			throw e;
		}
//FileNotFoundException   IOException     ParseException

		return response;
	}

	@Override
	public HashMap<String, Object> srvImportarBoletaRapiPago(MultipartFile archivo) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			String nameFile = archivo.getOriginalFilename();
			srvStorage.save(archivo, "temp");

			File comprobanteBoleta = new File("src//main//webapp//temp//" + nameFile);
			BufferedReader readFile = new BufferedReader(new FileReader(comprobanteBoleta));
			
			String caracteresLine;
			String rowHeaderInit = "00000000";
			List<String> detalleFile = new ArrayList<>();
			String rowFootInit = "99999999";
			while ((caracteresLine = readFile.readLine()) != null) {
				if(caracteresLine.substring(0, 8).compareTo(rowHeaderInit) != 0 && caracteresLine.substring(0, 8).compareTo(rowFootInit) != 0 ) {
					System.err.println(caracteresLine);
				}
				
				detalleFile.add(caracteresLine);
				
			}
			
			readFile.close();
			comprobanteBoleta.delete();
			
		} catch (FileNotFoundException file) {
			log.error("ERROR IMPORTACION BOLETA => " + file.toString());
		} catch (IOException io) {
			log.error("ERROR IMPORTACION BOLETA => " + io.toString());
//		} catch (ParseException pas) {
//			log.error("ERROR IMPORTACION BOLETA => " + pas.toString());
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
