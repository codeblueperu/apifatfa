package com.fatfa.model.serviceImp;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorConflictException;
import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.entity.DetalleBoletaConceptoModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.repository.IBoletaRepository;
import com.fatfa.model.repository.IDeclaradosRepository;
import com.fatfa.model.repository.IEmpresaRepository;
import com.fatfa.model.service.IBoletaService;
import com.fatfa.utils.Constantes;

@Service
public class BoletaServiceImpl implements IBoletaService {

	private static final Logger log = LoggerFactory.getLogger(BoletaServiceImpl.class);

	@Autowired
	private IDeclaradosRepository repoNomina;

	@Autowired
	private IBoletaRepository repoBoleta;

	@Autowired
	private IEmpresaRepository repoEmpresa;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public DetalleBoletaConceptoModel onCalcularMontoBoleta(int idEmpresa, String anio, String mes,
			int totalDiasInteres) {
		DetalleBoletaConceptoModel data = new DetalleBoletaConceptoModel();
//		# DEL SUELDO DEL TRABAJADOR 1%
		double MONTO_ART46 = 0;
//		# DEL SUELDO DEL TRABAJADOR + ANTIGUEDAD * 1%
		double MONTO_ART47 = 0;
//		# DEL SUELDO DEL TRABAJADOR 1%
		double MONTO_ART48 = 0;
//		# SEGUN ZONA
		double MONTO_CONTRIBUCION = 0;
//		# DEL DIA 11 MENOS EL ART 46
		double MONTO_INTERES = 0;
//		#MONTO SUBTOTAL BOLETA
		double MONTO_SUBTOTAL_BOLETA = 0;
//		# MONT TOTAL BOLETA
		double MONTO_BOLETA = 0;

		try {
			List<NominasModel> listaNomina = repoNomina.findByEmpresaIdEmpresaAndAnioAndMes(idEmpresa, anio, mes);

			for (NominasModel nomina : listaNomina) {
//				# CALCULO ART 46
				MONTO_ART46 += nomina.getSueldo() * 1 / 100;
//				# CALCULO ART 48 
				MONTO_ART48 += nomina.getSueldo() * 1 / 100;
//				# CALCULO ART 47 REMUNERACION INTEGRAL  <==> TIEMPO SERVICIO
				MONTO_ART47 += onObtenerRemuneracionIntegral(nomina.getIdNomina()) * 1 / 100;
//				# MONTO CONTRIBUCION SEGUN ZONA
				MONTO_CONTRIBUCION += nomina.getZona().getAporteZona();
			}

//			# CALCULAR EL SUBTOTAL DE LA BOLEAT
			MONTO_SUBTOTAL_BOLETA = MONTO_ART46 + MONTO_ART47 + MONTO_ART48 + MONTO_CONTRIBUCION;
//			#CALCULO INTERES POR DIA  O MES
			if (totalDiasInteres > 30) {
				MONTO_INTERES += MONTO_SUBTOTAL_BOLETA * 5.91;
			} else if (totalDiasInteres > 0 && totalDiasInteres < 30) {
				MONTO_INTERES += MONTO_SUBTOTAL_BOLETA * 0.19;
			}

//			#CALCULO TOTAL POSIBLE PAGO
			MONTO_BOLETA = MONTO_SUBTOTAL_BOLETA + MONTO_INTERES;
			data.setAporteArt46(new BigDecimal(MONTO_ART46).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setAporteArt47(new BigDecimal(MONTO_ART47).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setAporteArt48(new BigDecimal(MONTO_ART48).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setIntereses(new BigDecimal(MONTO_INTERES).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setTotalApagar(new BigDecimal(MONTO_BOLETA).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());

		} catch (Exception e) {
			log.error("ERROR CALCULAR BOLETA => " + e.toString());
			throw e;
		}
		return data;
	}

	@Override
	public double onObtenerRemuneracionIntegral(int idNomina) {

		return jdbcTemplate.queryForObject(
				"SELECT calcular_coeficiente_antiguedad(id_nomina,id_empresa,anio,mes,sueldo) FROM tb_nominas WHERE id_nomina = "
						+ idNomina,
				Double.class);
	}

	@Override
	public void onGenerarTalonBoletaPago(BoletaModel dataBoleta) {
		try {
//			# GUARDAR DATOS DE LA BOLETA
			BoletaModel newData = repoBoleta.save(dataBoleta);
//			#GENERAR CODIGO DE BARRA
			this.onGenerarCodigoBarraSegunTipoBanco(newData.getIdBoleta());

		} catch (Exception e) {
			log.error("ERROR GENERAR TALON BOLETA SEGUN TIPO BANCO => " + e.toString());
			throw e;
		}

	}

	@Override
	public String onGenerarCodigoBarraSegunTipoBanco(int idBoleta) {
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
			log.error("ERROR GENERAR CODIGO BARRA SEGUN TIPO BANCO => " + e.toString());
			throw e;
		}
		return codigoBarra;
	}

	@Override
	public BoletaModel prueba() {
		// TODO Auto-generated method stub
		return repoBoleta.findAll().get(0);
	}

	@Override
	public void onGenerarBoleta(int idBoleta, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
