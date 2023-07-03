package com.fatfa.model.serviceImp;

import java.io.File;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fatfa.model.repository.IBoletaImportacionRepository;
import com.fatfa.model.service.IBoletaImportacionService;
import com.fatfa.model.service.IFilesStorageService;

@Service
public class BoletaImportacionServiceImp implements IBoletaImportacionService {

	private static final Logger log = LoggerFactory.getLogger(BoletaImportacionServiceImp.class);
	
	@Autowired
	private IBoletaImportacionRepository repoBoletaImport;
	
	@Autowired
	private IFilesStorageService srvStorage;
	
	@Override
	public HashMap<String, Object> srvImportarBoleta(MultipartFile archivo) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			String nameFile = archivo.getOriginalFilename();
			srvStorage.save(archivo);
			
			 File doc = new File("C:\\Drive\\Learn.txt");
			response.put("name", archivo.getOriginalFilename());
		} catch (Exception e) {
			log.error("ERROR IMPRTACION BOLETA => "+ e.toString());
			throw e;
		}
		
		return response;
	}

}
