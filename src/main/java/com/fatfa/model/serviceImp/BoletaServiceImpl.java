package com.fatfa.model.serviceImp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.entity.DetalleBoletaConceptoModel;
import com.fatfa.model.entity.EstadoPagoModel;
import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.repository.IBoletaRepository;
import com.fatfa.model.repository.IDeclaradosRepository;
import com.fatfa.model.service.IBancosService;
import com.fatfa.model.service.IBoletaService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class BoletaServiceImpl implements IBoletaService {

	private static final Logger log = LoggerFactory.getLogger(BoletaServiceImpl.class);

	@Autowired
	private IDeclaradosRepository repoNomina;

	@Autowired
	private IBoletaRepository repoBoleta;

	@Autowired
	private IBancosService srvBancos;

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
	public void onGenerarTalonBoletaPago(BoletaModel dataBoleta, HttpServletRequest request,
			HttpServletResponse response) {
		BoletaModel boletaDB = new BoletaModel();
		String nameFile = "";
		try {
//			# GUARDAR DATOS DE LA BOLETA
			dataBoleta.setEstaoPago(new EstadoPagoModel(1));
			boletaDB = repoBoleta.save(dataBoleta);
//			#GENERAR CODIGO DE BARRA

//			BANCO DE LA NACION
			if (dataBoleta.getBanco().getIdBanco().trim().compareTo("4977") == 0) {

				srvBancos.onGeneraCodigigoBarraBancoNacion(boletaDB.getIdBoleta());
				nameFile = "boleta_banco_nacion.jrxml";
			}
//			PAGO FACIL
			else if (dataBoleta.getBanco().getIdBanco().trim().compareTo("1037") == 0) {

				srvBancos.onGeneraCodigoBarraPagoFacil(boletaDB.getIdBoleta());
				nameFile = "boleta_pago_facil.jrxml";
			} else {
				System.err.println(0);
				srvBancos.onGeneraCodigigoBarraBancoNacion(boletaDB.getIdBoleta());
				nameFile = "boleta_banco_nacion.jrxml";
			}

//			GENERAR BOLETA DE PAGO SEGUN EL TIPO DE BANCO
			onGenerarBoleta(boletaDB.getIdBoleta(), nameFile, request, response);

		} catch (Exception e) {
			log.error("ERROR GENERAR TALON BOLETA SEGUN TIPO BANCO => " + e.toString());
			throw e;
		}
		// return boletaDB;
	}

	@Override
	public void onGenerarBoleta(int idBoleta, String nameFile, HttpServletRequest request,
			HttpServletResponse response) {
		Connection connection = null;
		System.err.println(idBoleta);
		try {
			String rutaFile = request.getSession().getServletContext().getRealPath("/rpt/boletas/" + nameFile);
			JasperReport jasperReport = JasperCompileManager.compileReport(rutaFile);
			Map<String, Object> parametros = new HashMap<>();

			parametros.put("P_COD_BOLETA", idBoleta);

			byte[] reporte = null;

			connection = this.jdbcTemplate.getDataSource().getConnection();

			if (connection != null) {
				System.out.println("[connection connection] " + connection);
			}

			reporte = JasperRunManager.runReportToPdf(jasperReport, parametros, connection);

			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=BOLETANACONACION.pdf");
			response.setHeader("Cache-Control", "max-age=30");
			response.setHeader("Pragma", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setContentLength(reporte.length);
			ServletOutputStream out = response.getOutputStream();
			out.write(reporte, 0, reporte.length);
			out.flush();
			out.close();

		} catch (Exception e) {
			log.error("ERROR! GENERAR BOLETA RPT " + e.toString());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("ERROR! EN cerrar conexion");
				}
			}
		}

	}

}
