package com.fatfa.model.service;

import java.util.HashMap;
import org.springframework.web.multipart.MultipartFile;

public interface IBoletaImportacionService {

	HashMap<String, Object> srvImportarBoletaPagoFacil(MultipartFile archivo);
	
	HashMap<String, Object> srvImportarBoletaRapiPago(MultipartFile archivo);
}
