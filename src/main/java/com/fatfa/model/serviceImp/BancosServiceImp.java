package com.fatfa.model.serviceImp;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorConflictException;
import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.repository.IBoletaRepository;
import com.fatfa.model.repository.IEmpresaRepository;
import com.fatfa.model.service.IBancosService;
import com.fatfa.utils.Constantes;

@Service
public class BancosServiceImp implements IBancosService {
	private static final Logger log = LoggerFactory.getLogger(BancosServiceImp.class);

	@Autowired
	private IBoletaRepository repoBoleta;

	@Autowired
	private IEmpresaRepository repoEmpresa;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String onGeneraCodigigoBarraBancoNacion(int idBoleta) {
		
		String codigoBarra = "";
		try {
			BoletaModel boleta = repoBoleta.findById(idBoleta).orElseThrow(
					() -> new ErrorConflictException("Error al intentar buscar la boleta mediante su identificador."));
			EmpresasModel empresa = repoEmpresa.findById(boleta.getEmpresa().getIdEmpresa()).orElseThrow(
					() -> new ErrorConflictException("Error al intentar buscar empresa mediante su identificador."));

//			# CODIGO ASIGNADO POR EL BANCO 4 DIGITOS
			codigoBarra += boleta.getBanco().getIdBanco();
//			# IMPORTE TOTAL PAGAR 8 DIGITOS	<===> AUTOCOMLETAR CON 0 SI ES NECESARIO
			String importeTotal = boleta.getImporteTotal().toString().replace(",", "").replace(".", "");
			codigoBarra += StringUtils.leftPad(importeTotal, 8, "0");
//			# ANIO PERIODO 2 DIGITOS 
			codigoBarra += boleta.getAnio().substring(2, 4);
//			# 163 ?
			codigoBarra += 163;
//			#CUIT DE LA EMPRESA 11 DIGITOS
			codigoBarra += empresa.getCuit();
//			# MES PERIODO 2 DIGITOS
			codigoBarra += boleta.getMes();
//			# ULTIMO DIGITO ANIO PERIODO 1 DIGITO
			codigoBarra += boleta.getAnio().substring(3, 4);
//			# VALOR DEFAULT 0
			codigoBarra += "0";
//			# 0085500190 ????
			codigoBarra += "008550019";

			// SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			// codigoBarra += formatter.format(boleta.getFechaProbablePago());

//			# DIGITO VERIFICADOR 1 DIGITO
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

	@Override
	public String onGeneraCodigoBarraPagoFacil(int idBoleta) {
		String codigoBarra = "";
		
		try {
			BoletaModel boleta = repoBoleta.findById(idBoleta).orElseThrow(
					() -> new ErrorConflictException("Error al intentar buscar la boleta mediante su identificador."));
			EmpresasModel empresa = repoEmpresa.findById(boleta.getEmpresa().getIdEmpresa()).orElseThrow(
					() -> new ErrorConflictException("Error al intentar buscar empresa mediante su identificador."));

//			# IDENTIFICACION DE LA EMPRESA ===> 4 DIGITOS
			codigoBarra += boleta.getBanco().getIdBanco();
//			# IMPORTE A PAGAR BOLETA ===> 8 DIGITOS SI EL VALOR ES MENOR A 8 DIGITOS AUTOCOMPLETAR CON 0
			String importeTotal = boleta.getImporteTotal().toString().replace(",", "").replace(".", "");
			codigoBarra += StringUtils.leftPad(importeTotal, 8, "0");
//			# FECHA PRIMER VENCIMIENTO 5 DIGITOS ===> 
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatterAA = new SimpleDateFormat("yy");
//			# ==> OBTENER DIAS TRASCURRIDOS DEL PRIMER VENCIMIENTO
			int diasTranscurridoPrimerVencimiento = onCalcularDiferenciaFechas(
					formatter.format(boleta.getFechaPrimerVencimiento()));
			
			codigoBarra += formatterAA.format(boleta.getFechaPrimerVencimiento())
					+ StringUtils.leftPad(String.valueOf(diasTranscurridoPrimerVencimiento), 3, "0");
//			#CUIT DE LA EMPRESA 11 DIGITOS
			codigoBarra += empresa.getCuit();
//			# MES PERIODO 2 DIGITOS
			codigoBarra += boleta.getMes();
			
			
//			#UPDATE CODIGO BARRA BOLETA
			boleta.setCodigoBarras(codigoBarra);
			repoBoleta.save(boleta);
			
		} catch (Exception e) {
			log.error("ERROR GENERAR CODIGO BARRA PAGO FACIL => " + e.toString());
			throw e;
		}

		return codigoBarra;
	}

	@Override
	public int onCalcularDiferenciaFechas(String fechaVencimiento) {
		int diaTranscurridos = 0;
		String fechaInicial = Constantes.anioActual + "-01-01";
		diaTranscurridos = jdbcTemplate.queryForObject(
				"SELECT TIMESTAMPDIFF(DAY, '" + fechaInicial + "','" + fechaVencimiento + "') FROM dual",
				Integer.class);

		diaTranscurridos++;

		return diaTranscurridos;
	}

}
