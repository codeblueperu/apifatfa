package com.fatfa.model.serviceImp;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorConflictException;
import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.BancosModel;
import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.repository.IBancosRepository;
import com.fatfa.model.repository.IBoletaRepository;
import com.fatfa.model.repository.IEmpresaRepository;
import com.fatfa.model.service.IBancosService;
import com.fatfa.model.service.IGlobalService;
import com.fatfa.utils.Constantes;

@Service
public class BancosServiceImp implements IBancosService {
	private static final Logger log = LoggerFactory.getLogger(BancosServiceImp.class);

	@Autowired
	private IBoletaRepository repoBoleta;

	@Autowired
	private IEmpresaRepository repoEmpresa;

	@Autowired
	private IGlobalService srvGlobal;

	@Autowired
	private IBancosRepository repoBanco;

	@Override
	public String onGeneraCodigigoBarraBancoNacion(int idBoleta) {

		String codigoBarra = "";
		try {
//			# ESTE DIGITO CAMBIA CUANDO ES TRANSFERENCIA A 12
			int digitosMontoPagar = 8;
			BoletaModel boleta = repoBoleta.findById(idBoleta).orElseThrow(
					() -> new ErrorConflictException("Error al intentar buscar la boleta mediante su identificador."));
			EmpresasModel empresa = repoEmpresa.findById(boleta.getEmpresa().getIdEmpresa()).orElseThrow(
					() -> new ErrorConflictException("Error al intentar buscar empresa mediante su identificador."));

//			# CODIGO ASIGNADO POR EL BANCO 4 DIGITOS
			BancosModel bancoModel = repoBanco.findById(boleta.getBanco().getIdBanco())
					.orElseThrow(() -> new ErrorNotFoundException("No se encontro el Banco con el ID enviado :("));
			if (bancoModel.getIdBanco().compareTo("6") == 0 || bancoModel.getIdBanco().compareTo("7") == 0) {
				digitosMontoPagar = 12;
			}
			codigoBarra += bancoModel.getIdentificador();
//			# IMPORTE TOTAL PAGAR 8 DIGITOS	<===> AUTOCOMLETAR CON 0 SI ES NECESARIO
			BigDecimal montoDecimal = new BigDecimal(boleta.getImporteTotal()).setScale(2, RoundingMode.HALF_UP);
			String importeTotal = montoDecimal.toString().replace(",", "").replace(".", "");
			// System.err.println(importeTotal);
			codigoBarra += StringUtils.leftPad(importeTotal, digitosMontoPagar, "0");
//			# ANIO PERIODO 2 DIGITOS 
			codigoBarra += boleta.getAnio().substring(2, 4);
//			# DIFERENCIA DE DIAS ==> DESDE LA FECHA INICIO AF HASTA EL FECHA DE VENCIMIENTO
			String fechaInicial = Constantes.anioActual + "-01-01";
			int diasTranscurridoPrimerVencimiento = srvGlobal.onCalcularDiferenciaFechas(fechaInicial,
					Constantes.utilFormatoFecha(boleta.getFechaPrimerVencimiento(), "yyyy-MM-dd"));
			codigoBarra += StringUtils.leftPad(String.valueOf(diasTranscurridoPrimerVencimiento), 3, "0");
//			#CUIT DE LA EMPRESA 11 DIGITOS
			codigoBarra += empresa.getCuit();
//			# MES PERIODO 2 DIGITOS
			codigoBarra += boleta.getMes();
//			# ULTIMO DIGITO ANIO PERIODO 1 DIGITO
			codigoBarra += boleta.getAnio().substring(3, 4);
//			# VALOR DEFAULT 0 TIPO DE MONEDA
			codigoBarra += "0";
//			# VALOR TOTAL DE LA BOLETA * INTERES DIARIO / 2 DECIMALES | 6 DIGITOS
			double intx = boleta.getImporteTotal() * 1.333333 / 100;
			codigoBarra += StringUtils
					.leftPad(String.valueOf(new BigDecimal(intx).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue())
							.replace(".", "").replace(",", ""), 6, "0");
//			#DIFERENCIA DE DIAS DEL SEGUNDO VENCIMIENTO
			codigoBarra += "01";
//			# PRIMER DIGITO VERIFICADOR		
			codigoBarra += Constantes.generarDigitoVerificador(codigoBarra);
//			# SEGUNDO DIGITO VERIFICADOR		
			codigoBarra += Constantes.generarDigitoVerificador(codigoBarra);

//			#UPDATE CODIGO BARRA BOLETA
			boleta.setCodigoBarras(codigoBarra);
			repoBoleta.save(boleta);

		} catch (Exception e) {
			log.error("ERROR GENERAR CODIGO BARRA BANCO NACION => " + e.toString());
			throw e;
		}
		return codigoBarra;
	}

}
