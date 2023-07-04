package com.fatfa.model.serviceImp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.repository.IBoletaImportacionRepository;
import com.fatfa.model.repository.IBoletaRepository;
import com.fatfa.model.service.IBoletaImportacionService;
import com.fatfa.model.service.IFilesStorageService;
import com.fatfa.utils.Constantes;

@Service
public class BoletaImportacionServiceImp implements IBoletaImportacionService {

	private static final Logger log = LoggerFactory.getLogger(BoletaImportacionServiceImp.class);

	@Autowired
	private IBoletaImportacionRepository repoBoletaImport;
	
	@Autowired
	private IBoletaRepository srvBoleta;

	@Autowired
	private IFilesStorageService srvStorage;

	@Override
	public HashMap<String, Object> srvImportarBoleta(MultipartFile archivo) {
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

			BoletaModel boletaBuscar = new BoletaModel();
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
						String probaleCodigoBarras = strng.substring(1, 81).trim();
						//System.err.println(strng.substring(1, 81).trim());
						detalleFile.add(probaleCodigoBarras);
						if(boletaBuscar == null) {
							boletaBuscar = srvBoleta.findByCodigoBarrasAndEstadoPagoIdEstadoPago(probaleCodigoBarras,
									1);
							
						}
						
					}
				}

			}
			readFile.close();
			comprobanteBoleta.delete();
			System.err.println(boletaBuscar);
//			String v = "6103700020000231821111111111105300002670178";
//			System.err.println(v.substring(0, 1));
			response.put("name", archivo.getOriginalFilename());
			response.put("cabecera", cabeceraFile);
			response.put("fechaProceso", fechaProcesadaFile);
			response.put("subCabecera", cabeceraLoteFile);

		} catch (Exception e) {
			log.error("ERROR IMPORTACION BOLETA => " + e.toString());
			// throw e;
		}

		return response;
	}

}
