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
			try (BufferedReader readFile = new BufferedReader(new FileReader(comprobanteBoleta))) {
				String cabeceraFile = "";
				String cabeceraLoteFile = "";
				String fechaProcesadaFile = "";

				String probaleCodigoBarras = "";

				String strng;
				while ((strng = readFile.readLine()) != null) {
					List<String> detalleFile = new ArrayList<>();
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
						detalleFile.add(strng);
						if (strng.substring(0, 1).compareTo("6") == 0) {
//						CODIGO DE BARRA
							probaleCodigoBarras = strng.substring(1, 81).trim();
//						BUSCAMOS LA BOLETA SEGUN CODIGO DE BARRA
							BoletaModel boletaBuscar = repoBoleta
									.findByCodigoBarrasAndEstadoPagoIdEstadoPago(probaleCodigoBarras, 1);
							
							if (boletaBuscar != null) {
								String fechaPago = fechaProcesadaFile.substring(0, 4) + "-"
										+ fechaProcesadaFile.substring(4, 6) + "-" + fechaProcesadaFile.substring(6, 8);
//							GUARDAR DATOS DE LA IMPORTACION
								BoletasImportacionModel importarData = new BoletasImportacionModel();
								importarData.setFechaImportacion(new Date());
								importarData.setFechaPago(Constantes.utilConvertirFecha(fechaPago, "yyy-MM-dd"));
								importarData.setImporteCobrado(boletaBuscar.getImporteTotal());
								importarData.setNombreArchivoimportacion(nameFile);
								importarData.setBoleta(boletaBuscar);
								importarData.setMedioPago(boletaBuscar.getBanco());
								importarData.setContenidoFile(detalleFile.toString());
//							OBTENER USUARIO DEL LOGIN
								Authentication auth = SecurityContextHolder.getContext().getAuthentication();
								if (auth != null) {
									UsuarioModel userLogin = srvUser.srvBuscarUsuarioEmail(auth.getPrincipal().toString());
									importarData.setIdUserLogin(userLogin.getIdUsuario());
//								GUARDAMOS LA IMPORTACION
									repoBoletaImport.save(importarData);
//								ACTUALIZAR ESTADO DE LA BOLETA
									boletaBuscar.setEstadoPago(new EstadoPagoModel(2));
									repoBoleta.save(boletaBuscar);

								} else {
									throw new ErrorConflictException("No se pudo obtener respuesta de tu servidor :(");
								}

							}

						}
						if (strng.substring(0, 1).compareTo("7") == 0) {
							detalleFile = new ArrayList<>();
						}
					}
				}
				readFile.close();
				comprobanteBoleta.delete();

				response.put("message", "El archivo fue importado correctamente.");
				response.put("cabecera", cabeceraFile);
				response.put("subCabecera", cabeceraLoteFile);
			}

		} catch (FileNotFoundException file) {
			log.error("ERROR IMPORTACION BOLETA => " + file.toString());
		} catch (IOException io) {
			log.error("ERROR IMPORTACION BOLETA => " + io.toString());
		} catch (ParseException pas) {
			log.error("ERROR IMPORTACION BOLETA => " + pas.toString());
		} catch (Exception e) {
			throw e;
		}

		return response;
	}

	@Override
	public HashMap<String, Object> srvImportarBoletaRapiPago(MultipartFile archivo) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			String nameFile = archivo.getOriginalFilename();
			srvStorage.save(archivo, "temp");

			File comprobanteBoleta = new File("src//main//webapp//temp//" + nameFile);
			try (BufferedReader readFile = new BufferedReader(new FileReader(comprobanteBoleta))) {
				String caracteresLine;
//			ESTO LO DICE LA DOCUMENTACION DEL BANCO
				String rowHeaderInit = "00000000";
//			SON VALORES DE LA CABECERA
				String rowFootInit = "99999999";
				while ((caracteresLine = readFile.readLine()) != null) {
					if (caracteresLine.substring(0, 8).compareTo(rowHeaderInit) != 0
							&& caracteresLine.substring(0, 8).compareTo(rowFootInit) != 0) {
//					ALMACENAR EL DETALLE DEL ARCHIVO SEGUN FILA
						List<String> detalleFile = new ArrayList<>();
//					CAPTURAR FECHA DE PROCESO
						String fechaProcesa = caracteresLine.substring(0, 8);
//					CAPTURAR CODIGO DE BARRAS PARA BUSCAR LA BOLETA
						String codigobarra = caracteresLine.substring(23, 65);
//					ADD DETALLE
						detalleFile.add(caracteresLine);
//					BUSCAR BOLETA SEGUN EL CODIGO DE BARRAS Y CON ESTADO PENDIENTE DE PAGO
						BoletaModel boletaBuscar = repoBoleta.findByCodigoBarrasAndEstadoPagoIdEstadoPago(codigobarra,
								1);
//					GUARDAMOS EL DETALLE DE IMPORTACION DE FILE
						if (boletaBuscar != null) {
							String fechaPago = fechaProcesa.substring(0, 4) + "-" + fechaProcesa.substring(4, 6) + "-"
									+ fechaProcesa.substring(6, 8);
//						CUERPO DE IMPORTACION
							BoletasImportacionModel importarData = new BoletasImportacionModel();
							importarData.setFechaImportacion(new Date());
							importarData.setFechaPago(Constantes.utilConvertirFecha(fechaPago, "yyy-MM-dd"));
							importarData.setImporteCobrado(boletaBuscar.getImporteTotal());
							importarData.setNombreArchivoimportacion(nameFile);
							importarData.setBoleta(boletaBuscar);
							importarData.setMedioPago(boletaBuscar.getBanco());
							importarData.setContenidoFile(detalleFile.toString());
//						OBTENER EL ID DEL USUARIO LOGUEADO
							Authentication auth = SecurityContextHolder.getContext().getAuthentication();
							if (auth != null) {
								UsuarioModel userLogin = srvUser.srvBuscarUsuarioEmail(auth.getPrincipal().toString());
								importarData.setIdUserLogin(userLogin.getIdUsuario());
//							GUARDAMOS
								repoBoletaImport.save(importarData);
//							ACTUALIZAMOS EL ESTADO DE LA BOLETA AL ESTADO PAGADO
								boletaBuscar.setEstadoPago(new EstadoPagoModel(2));
								repoBoleta.save(boletaBuscar);

							} else {
								throw new ErrorConflictException("No se pudo obtener respuesta de tu servidor :(");
							}
						}
//					System.err.println(boletaBuscar);
//					System.err.println(caracteresLine);
//					System.err.println(caracteresLine.substring(23, 65));
					}

				}

				readFile.close();
			}

			comprobanteBoleta.delete();
			response.put("message", "El archivo fue importado correctamente.");

		} catch (FileNotFoundException file) {
			log.error("ERROR IMPORTACION BOLETA => " + file.toString());
		} catch (IOException io) {
			log.error("ERROR IMPORTACION BOLETA => " + io.toString());
		} catch (ParseException pas) {
			log.error("ERROR IMPORTACION BOLETA => " + pas.toString());
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
