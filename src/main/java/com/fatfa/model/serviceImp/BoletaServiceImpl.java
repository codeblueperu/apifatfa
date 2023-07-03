package com.fatfa.model.serviceImp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.dto.MatrizPeriodoIntersDTO;
import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.entity.DetalleBoletaConceptoModel;
import com.fatfa.model.entity.EstadoPagoModel;
import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.entity.TablaSueldoBasicoModel;
import com.fatfa.model.repository.IBoletaRepository;
import com.fatfa.model.repository.IDeclaradosRepository;
import com.fatfa.model.repository.ISueldoBasicoRepository;
import com.fatfa.model.service.IBancosService;
import com.fatfa.model.service.IBoletaService;
import com.fatfa.model.service.IGlobalService;
import com.fatfa.utils.Constantes;

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
	private IGlobalService srvGlobal;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ISueldoBasicoRepository repoTablaSueldo;

	@Override
	public DetalleBoletaConceptoModel onCalcularMontoBoleta(int idEmpresa, String anioPeriodoPago,
			String mesPeriodoPago, Date fechaPosiblePago) {
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
//		# MONTO SUBTOTAL BOLETA
		double MONTO_SUBTOTAL_BOLETA = 0;
//		# MONT TOTAL BOLETA
		double MONTO_BOLETA = 0;
//		# TOTAL DE DIAS INTERESES
		int totalDiasInteres = 0;

		try {
//			#OBTENER NOMINA DE EMPRESA PERIDO PAGO
			List<NominasModel> listaNomina = repoNomina.findByEmpresaIdEmpresaAndAnioAndMes(idEmpresa, anioPeriodoPago,
					mesPeriodoPago);
//			# VALIDAR QUE LA LISTA DE NOMINA NO ESTA VACIO
			listaNomina.stream().findFirst().orElseThrow(
					() -> new ErrorNotFoundException("No se encontroo ninguna nomina para el periodo de trabajo <b>"
							+ mesPeriodoPago + " - " + anioPeriodoPago + "</b>"));

//			#OBTENER LA DIFERENCIA DE DIAS DE LA FECHA PROPABLE PAGO Y LA FECHA DE VENCIMIENTO ES 10 DE CADA MES PERO EL INTERES CORRE APARTIR DEL 11 POR ESO PONGO FIJO 11 SEGUN EL PERIODO APAGAR
			totalDiasInteres = srvGlobal.onCalcularDiferenciaFechas(anioPeriodoPago + "-" + mesPeriodoPago + "-11",
					Constantes.utilFormatoFecha(fechaPosiblePago, "yyyy-MM-dd"));
			// System.err.println(totalDiasInteres);
			for (NominasModel nomina : listaNomina) {
//				# OBTENER EL SUELDO SEGUN EL PERIODO 
				TablaSueldoBasicoModel escalaSueldo = repoTablaSueldo
						.findByIdCategoriaAndPeriodo(nomina.getCategoria().getIdCategoria(),
								mesPeriodoPago + anioPeriodoPago.substring(2, 4))
						.orElseThrow(() -> new ErrorNotFoundException(
								"No se encontro la escala de sueldo del trabajador para la categoria <b>"
										+ nomina.getCategoria().getCategoria() + "</b> en el periodo <b>"
										+ mesPeriodoPago + anioPeriodoPago.substring(2, 4) + "</b>"));
//				# CALCULO ART 46
				MONTO_ART46 += escalaSueldo.getSueldoBasico() * 1 / 100;
//				# CALCULO ART 48 
				MONTO_ART48 += escalaSueldo.getSueldoBasico() * 1 / 100;
//				# CALCULO ART 47 REMUNERACION INTEGRAL  <==> TIEMPO SERVICIO
//				System.err.println(srvGlobal.onObtenerRemuneracionIntegral(nomina.getIdNomina(),
//						escalaSueldo.getSueldoBasico()));
				MONTO_ART47 += srvGlobal.onObtenerRemuneracionIntegral(nomina.getIdNomina(),
						escalaSueldo.getSueldoBasico()) * 1 / 100;
//				# MONTO CONTRIBUCION SEGUN ZONA
				MONTO_CONTRIBUCION += nomina.getZona().getAporteZona();
			}

//			# CALCULAR EL SUBTOTAL DE LA BOLEAT
			MONTO_SUBTOTAL_BOLETA = new BigDecimal(MONTO_ART46).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
					+ new BigDecimal(MONTO_ART47).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
					+ new BigDecimal(MONTO_ART48).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
					+ new BigDecimal(MONTO_CONTRIBUCION).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
//			#CALCULO INTERES POR DIA
			if (totalDiasInteres > 0 && totalDiasInteres <= 30) {
				List<MatrizPeriodoIntersDTO> listaMatriz = srvGlobal.onObtenerMatrizPerido();
				double valorInteres = 0;
				for (MatrizPeriodoIntersDTO matriz : listaMatriz) {
					if (matriz.getPeriodo().compareTo(anioPeriodoPago + "-" + mesPeriodoPago) == 0) {
						valorInteres = matriz.getInteresDiario();
						break;
					}
				}
				valorInteres = new BigDecimal(valorInteres).setScale(3, BigDecimal.ROUND_HALF_DOWN).doubleValue();

				MONTO_INTERES = (MONTO_ART47 + MONTO_ART48) * valorInteres / 100;

				MONTO_INTERES = new BigDecimal(MONTO_INTERES).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
						* totalDiasInteres;
			}
//			#INTERES POR MES
			else if (totalDiasInteres > 30) {
//				# OBTENER EL AÑO DEL PROBABLE PAGO
				String anioProbablePago = Constantes.utilFormatoFecha(fechaPosiblePago, "yyyy");
//				# OBTENER EL MES DEL PROBABLE PAGO
				String periodoString = anioProbablePago + "-" + Constantes.utilFormatoFecha(fechaPosiblePago, "MM");
//				# OBTENER LA MATRIZ DEL INTEREZ DESDE EL PERIODO PAGO HASTA LA FECHA PROBABLE PAGO
				List<MatrizPeriodoIntersDTO> listaMatrizInteres = srvGlobal.onObtenerMatrizPerido();
//				# ALMACENAR LA SUMA TODAL DE LOS INTERECES
				double totalInteresMensual = 0;
//				# RECORRER DESDE EL AÑO DE PERIODO PAGO HASTA LA FECHA PROBALE PAGO
				for (int i = Integer.parseInt(anioPeriodoPago); i <= Integer.parseInt(anioProbablePago); i++) {
//					# MES INICIAL DEL PERIODO
					int initMes = 0;
					if (Integer.parseInt(anioPeriodoPago) == i) {
						initMes = Integer.parseInt(mesPeriodoPago);
					} else {
						initMes = 1;
					}
//					# RECORRER LOS MESES DEL N? HASTA N..2
					for (int j = initMes; j <= 12; j++) {
						String periodoMesBuscar = i + "-" + Constantes.completeCeroIzquierda(String.valueOf(j), 2);
//						# RECORRER LA MATRIZ Y BUSCAR EL VALOR INTERS
						for (MatrizPeriodoIntersDTO matriz : listaMatrizInteres) {
							if (matriz.getPeriodo().compareTo(periodoMesBuscar) == 0) {
								totalInteresMensual += matriz.getInteresMensual();
								break;
							}
						}
//						# ROMPER EL PUBLE HASTA EL EL ANIO Y MES DE LA FECHA PROBABLE PAGO
						if (periodoString
								.compareTo(i + "-" + Constantes.completeCeroIzquierda(String.valueOf(j), 2)) == 0) {
							break;
						}
					}
				}
				totalInteresMensual = new BigDecimal(totalInteresMensual).setScale(3, BigDecimal.ROUND_HALF_DOWN)
						.doubleValue();
//				System.err.println(totalInteresMensual);
				MONTO_INTERES = (MONTO_ART47 + MONTO_ART48) * totalInteresMensual / 100;
//				System.err.println(MONTO_ART47 + MONTO_ART48);
//				System.err.println(totalInteresMensual / 100);
				MONTO_INTERES = new BigDecimal(MONTO_INTERES).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
			}

//			#CALCULO TOTAL POSIBLE PAGO
			// System.err.println(MONTO_SUBTOTAL_BOLETA);
			MONTO_BOLETA = new BigDecimal(MONTO_SUBTOTAL_BOLETA).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
					+ MONTO_INTERES;
			data.setAporteArt46(new BigDecimal(MONTO_ART46).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setAporteArt47(new BigDecimal(MONTO_ART47).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setAporteArt48(new BigDecimal(MONTO_ART48).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setIntereses(new BigDecimal(MONTO_INTERES).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setTotalApagar(new BigDecimal(MONTO_BOLETA).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			data.setContribucionExtraordinaria(
					new BigDecimal(MONTO_CONTRIBUCION).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());

		} catch (Exception e) {
			log.error("ERROR CALCULAR BOLETA => " + e.toString());
			throw e;
		}
		return data;
	}

	@Override
	public void onGenerarTalonBoletaPago(BoletaModel dataBoleta, HttpServletRequest request,
			HttpServletResponse response) {
		BoletaModel boletaDB = new BoletaModel();
		String nameFile = "";
		try {
//			# GUARDAR DATOS DE LA BOLETA SIEMPRE CON ESTADO PENDIENTE
			dataBoleta.setEstaoPago(new EstadoPagoModel(1));
			
//			#BUSCAR SI YA TIENE UNA BOLETA GENERADA ANTERIORMENTE PARA EL MISMO PERIODO
			Optional<BoletaModel> validarBoleta = repoBoleta
					.findByMesAndAnioAndEmpresaIdEmpresaAndEstadoPagoIdEstadoPago(dataBoleta.getMes(),
							dataBoleta.getAnio(), dataBoleta.getEmpresa().getIdEmpresa(),
							dataBoleta.getEstadoPago().getIdEstadoPago());
			
			if (validarBoleta.isPresent()) {
//				#DAR DE BAJA A LA BOLETA GENERADA ANTERIORMENTE
				BoletaModel boletaBaja = validarBoleta.get();
				boletaBaja.setEstaoPago(new EstadoPagoModel(3));
//				# DAMOS DE BAJA A LA BOLETA PARA PODER GUARDAR LA NUEVA RECTIFICATIVA
				repoBoleta.save(boletaBaja);
			}

//			# PROCEDEMOS A GUARDAR LA BOLETA Y SU DETALLE
			boletaDB = repoBoleta.save(dataBoleta);
			
//			#GENERAR CODIGO DE BARRA
			srvBancos.onGeneraCodigigoBarraBancoNacion(boletaDB.getIdBoleta());

//			# PROCEDEMOS A GENERAL EL REPORTE DE LA BOLETA SEGUN EL TIPO DE BANCO
//			BANCO DE LA NACION
			if (dataBoleta.getBanco().getIdBanco().trim().compareTo("2") == 0) {				
				nameFile = "boleta_banco_nacion.jrxml";
			}
//			PAGO FACIL
			else if (dataBoleta.getBanco().getIdBanco().trim().compareTo("1") == 0) {				
				nameFile = "boleta_pago_facil.jrxml";
			}
//			RAPI PAGO
			else if (dataBoleta.getBanco().getIdBanco().trim().compareTo("4") == 0) {				
				nameFile = "boleta_rapi_pago.jrxml";
			}
//			BAPRO
			else if (dataBoleta.getBanco().getIdBanco().trim().compareTo("3") == 0) {
				nameFile = "boleta_bapro.jrxml";
			}
//			PAGO MIS CUENTAS
			else if (dataBoleta.getBanco().getIdBanco().trim().compareTo("5") == 0) {
				nameFile = "boleta_pago_mis_cuentas.jrxml";
			}
			else {				
				nameFile = "boleta_banco_nacion.jrxml";
			}

//			#GENERAR RPT BOLETA
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
		
		try {
			String RUTA_REPOSITORY = request.getSession().getServletContext().getRealPath("");
			String rutaFile = request.getSession().getServletContext().getRealPath("/rpt/boletas/" + nameFile);
			JasperReport jasperReport = JasperCompileManager.compileReport(rutaFile);
			Map<String, Object> parametros = new HashMap<>();

			parametros.put("P_COD_BOLETA", idBoleta);
			parametros.put("P_RUTA", RUTA_REPOSITORY);

			byte[] reporte = null;

			connection = this.jdbcTemplate.getDataSource().getConnection();

			if (connection != null) {
				System.out.println("[connection connection] " + connection);
			}

			reporte = JasperRunManager.runReportToPdf(jasperReport, parametros, connection);

			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename="+nameFile.replace(".jrxml", ".pdf"));
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

	@Override
	public List<BoletaModel> srvLisBoleta(int idEmpresa, int idAporte, String mes, String anio) {
		
		List<BoletaModel> boletas= new ArrayList<>();
		
		if(idEmpresa == 0) {
			boletas = repoBoleta.findAll();
		}else {
			boletas = repoBoleta.findByEmpresaIdEmpresaAndAporteSindicalIdAporteAndMesAndAnio(idEmpresa, idAporte, mes, anio);
		}
		return boletas;
	}
}
