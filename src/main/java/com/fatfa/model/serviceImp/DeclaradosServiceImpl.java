package com.fatfa.model.serviceImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.entity.SindicatosModel;
import com.fatfa.model.entity.ZonasModel;
import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.controller.StorageController;
import com.fatfa.model.entity.CategoriasModelo;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.repository.ICategoriasRepository;
import com.fatfa.model.repository.IDeclaradosRepository;
import com.fatfa.model.repository.IEmpresaRepository;
import com.fatfa.model.repository.ISindicatoRepository;
import com.fatfa.model.repository.IZonasRepository;
import com.fatfa.model.service.IDeclaradosService;
import com.fatfa.utils.Constantes;

@Service
public class DeclaradosServiceImpl implements IDeclaradosService {
	private String ruta = "src//main//webapp//";
	private static final Logger log = LoggerFactory.getLogger(DeclaradosServiceImpl.class);

	@Autowired
	private IDeclaradosRepository repoDeclarados;

	@Autowired
	private IEmpresaRepository repoEmpresa;

	@Autowired
	private ICategoriasRepository repoCategoria;

	@Autowired
	private ISindicatoRepository repoSindicato;

	@Autowired
	private IZonasRepository repoZona;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, Object> srvAgregarDeclarados(NominasModel declarados) {
		Map<String, Object> map = new HashMap<>();
		try {
			NominasModel datos = new NominasModel();
			datos = repoDeclarados.save(declarados);

			map.put("data", datos);
			map.put("message", "El Declarado fue registrado con éxito");
		} catch (Exception e) {
			log.error("ERROR GUARDAR DECLARADO => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	@Transactional
	public List<NominasModel> srvCopiarDeclarados(List<NominasModel> declarados) {
		List<NominasModel> datos = new ArrayList<>();
		try {
			datos = repoDeclarados.saveAll(declarados);
		} catch (Exception e) {
			log.error("ERROR GUARDAR COPIA DE LA NÓMINA => " + e.toString());
			throw e;
		}
		return datos;
	}

	@Override
	public List<NominasModel> srvBuscarDeclarados(EmpresasModel empresa, String mes, String anio,
			Integer rectificativa) {
		List<NominasModel> datos = new ArrayList<>();
		try {
			datos = repoDeclarados.findByEmpresaAndMesAndAnioAndRectificativa(empresa, mes, anio, rectificativa);
		} catch (Exception e) {
			log.error("ERROR LISTAR DATOS => " + e.toString());
			throw e;
		}
		return datos;
	}

	@Override
	public String saveFile(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(ruta + file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return "Archivo guardado correctamente";
	}

	@Override
	public NominasModel srvBuscarDeclaradosID(int id) {
		NominasModel datos = new NominasModel();
		try {
			datos = repoDeclarados.findById(id).orElseThrow(() -> new ErrorNotFoundException(
					"El emial <b>" + id + "</b>, no se encuentra resgistrado en nuestra base de datos."));
		} catch (Exception e) {
			log.error("ERROR AL BUSCAR DECLARADO => " + e.toString());
			throw e;
		}
		return datos;
	}

	@Override
	public Map<String, Object> srvGuardarNominaMasiva(MultipartFile fileExcel, int id_empresa, String anio, String mes,
			int rectificativa) {
		Map<String, Object> map = new HashMap<>();
		// HttpServletRequest request = new HttpServletRequest();
		File fileExcelMasivo = null;
		try {
//			#VALIDAR QUE EL ARCHIVO EXCEL EXISTA
			if (fileExcel.isEmpty()) {
				throw new ErrorNotFoundException(
						"Para continuar con e proceso se solicita un archivo con la extension (.xlsx).");
			}

//			#PROCESAR EL GUARDADO TEMPORAL DEL ARCHIVO
			byte[] bytes = fileExcel.getBytes();
			Path path = Paths.get("src//main//webapp//temp//" + fileExcel.getOriginalFilename());
			Files.write(path, bytes);
//			#PROCEDEMOS CON LA LECTURA DEL ARCHIVO
			fileExcelMasivo = new File("src//main//webapp//temp//" + fileExcel.getOriginalFilename());
			FileInputStream inputStream = new FileInputStream(fileExcelMasivo);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);

			DataFormatter formatter = new DataFormatter();
//			#LISTA DE DATA A PROCESAR SIN ERRORES
			List<NominasModel> listNomina = new ArrayList<>();

//			#CONTADOR DE FILAS
			int contadorRow = 1;
//			#CONTADOR DE ERRORES
			int errorCount = 0;
//			#CREAR UNA LISTA PARA LOS ERRORES
			List<String> logErrores = new ArrayList<>();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 1; i < firstSheet.getLastRowNum(); i++) {
				Row row = firstSheet.getRow(i);
				contadorRow++;
				String CUIT_EMPRESA = formatter.formatCellValue(row.getCell(0));
				if (!CUIT_EMPRESA.isEmpty()) {
					NominasModel nominaUser = new NominasModel();
//            		#VALIDAR SI EL CUIT DE LA EMPRESA ES CORRECTA
					Optional<EmpresasModel> validEmpresa = repoEmpresa
							.findByCuit(formatter.formatCellValue(row.getCell(0)));
//					#SI LA EMPRESA EXISTE PROCEDEMOS CON EL ARMADO DEL MODELO
					if (validEmpresa.isPresent()) {

//						#EMPRESA 
						nominaUser.setEmpresa(validEmpresa.get());

//						#CUIL DEL TRABAJADOR
						String cuil_trabajdor = formatter.formatCellValue(row.getCell(2));
						if (!cuil_trabajdor.isEmpty() && (cuil_trabajdor.length() == 11)) {
							nominaUser.setCuil(cuil_trabajdor);
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "CUIL",
									"El valor del campo es requerido y no puede ser vacio y debe de contener como minimo 11 caracteres."));
						}

//						#NOMBRE DEL TRABAJADOR
						String nombreTrabajador = formatter.formatCellValue(row.getCell(3));
						Pattern paterLetras = Pattern.compile(Constantes.EXPRESION_REGULAR_SOLO_LETRAS);
						Matcher matcherNombreTrabajador = paterLetras.matcher(nombreTrabajador.replace(" ", ""));
						if (!nombreTrabajador.isEmpty() && (matcherNombreTrabajador.matches())) {
							nominaUser.setNombres(nombreTrabajador);
						} else {
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "NOMBRES",
									"El valor del campo es requerido y no puede ser nulo y solo debe de contener letras."));
						}

//						#FECHA DE INGRESO
						Pattern paterFecha = Pattern.compile(Constantes.EXPRESION_REGULAR_FORMATO_FECHA_YYYY_MM_DD);
						Matcher matcherFechaIngreso = paterFecha.matcher(formatter.formatCellValue(row.getCell(16)));
						if (matcherFechaIngreso.matches()) {
							nominaUser.setFechaIngreso(formatoFecha.parse(formatter.formatCellValue(row.getCell(16))));
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "FECHA INGRESO",
									"El valor del campo no coincide con el formato solicitado (yyyy-MM-dd) <===> ("
											+ formatter.formatCellValue(row.getCell(16)) + ")"));
						}

//						#CATEGORIA
						Optional<CategoriasModelo> validcategoria = repoCategoria
								.findByCategoria(formatter.formatCellValue(row.getCell(4)).toUpperCase());
						if (validcategoria.isPresent()) {
							nominaUser.setCategoria(new CategoriasModelo(validcategoria.get().getIdCategoria()));
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "CATEGORIA",
									"El valor del campo no coincide con ninguna categoria registrada, es posible que la CATEGORIA este mal digitado  y/ó vacio"));
						}

//						#SUELDO
						Pattern paterNumericDecimales = Pattern.compile(Constantes.EXPRESION_REGULAR_NUMEROS_DECIMALES);
						String sueldoTrabajador = formatter.formatCellValue(row.getCell(5));
						Matcher mat = paterNumericDecimales.matcher(sueldoTrabajador);
//						#VALIDAR SI CUMPLE LA EXPRECION REGULAR
						if (mat.matches()) {
							XSSFCell sueldoCell = (XSSFCell) row.getCell(5);
							BigDecimal sueldo_trabajador = new BigDecimal(sueldoCell.getNumericCellValue()).setScale(2,
									BigDecimal.ROUND_HALF_DOWN);
							nominaUser.setSueldo(sueldo_trabajador.doubleValue());
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "SUELDO",
									"El valor del campo no cumple con el formato adecuado (00000.00) <=====> ("
											+ sueldoTrabajador + ")"));
						}

//						#ESTADO BAJA
						if (formatter.formatCellValue(row.getCell(6)).length() == 1) {
							String estadoBaja = formatter.formatCellValue(row.getCell(6));
							if (estadoBaja.compareTo("S") == 0) {
								nominaUser.setEstadoBaja(true);

//								#VALIDAMOS LA FECHA DE EGRESO SOLO CUANDO EL ESTADO DE BAJA ES (SI)
								String fechaEgreso = formatter.formatCellValue(row.getCell(17));
								Matcher matcherFechaEgreso = paterFecha.matcher(fechaEgreso);
								if (!fechaEgreso.isEmpty()) {
//									#VALIDAR EL FORMATO ADECUADO DE LA FECHA
									if (matcherFechaEgreso.matches()) {
										nominaUser.setFechaEgreso(formatoFecha.parse(fechaEgreso));
									} else {
										errorCount++;
										logErrores.add(estructuraLogCargaMasiva(contadorRow, "FECHA EGRESO",
												"El valor del campo no coincide con el formato solicitado (yyyy-MM-dd) <===> ("
														+ fechaEgreso + ")"));
									}
								} else {
									errorCount++;
									logErrores.add(estructuraLogCargaMasiva(contadorRow, "FECHA EGRESO",
											"El valor del campo es requerido cuando un empleado tiene el ESTADO DE BAJA EN (S) y no puede ser vacio"));
								}

//								#VALIDAMOS LA FECHA BAJA
								String fechaBaja = formatter.formatCellValue(row.getCell(18));
								Matcher matcherFechaBaja = paterFecha.matcher(fechaBaja);
								if (!fechaBaja.isEmpty()) {
//									#VALIDAR EL FORMATO ADECUADO DE LA FECHA
									if (matcherFechaBaja.matches()) {
										nominaUser.setFechaBaja(formatoFecha.parse(fechaBaja));
									} else {
										errorCount++;
										logErrores.add(estructuraLogCargaMasiva(contadorRow, "FECHA BAJA",
												"El valor del campo no coincide con el formato solicitado (yyyy-MM-dd) <===> ("
														+ fechaBaja + ")"));
									}
								} else {
									errorCount++;
									logErrores.add(estructuraLogCargaMasiva(contadorRow, "FECHA BAJA",
											"El valor del campo es requerido cuando un empleado tiene el ESTADO DE BAJA EN (S) y no puede ser vacio"));
								}

							} else if (estadoBaja.compareTo("N") == 0) {
								nominaUser.setEstadoBaja(false);
							} else {
								errorCount++;
								logErrores.add(estructuraLogCargaMasiva(contadorRow, "ESTADO BAJA",
										"El valor del campo solo puede contener 1 caracter (S) ó (N) y no  <====> ("
												+ estadoBaja + ")"));
							}
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "ESTADO BAJA",
									"El valor del campo es requerido y solo puede contener 1 caracter (S) ó (N)."));
						}

//						#FECHA PROCESA
						nominaUser.setFechaProcesa(new Date());

//						#JORNADA REDUCIDA
						if (formatter.formatCellValue(row.getCell(7)).length() == 2) {
							nominaUser.setJornadaReducida(formatter.formatCellValue(row.getCell(7)).toUpperCase());
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "JORNADA REDUCIDA",
									"El valor del campo es requerido y solo puede contener 2 caracteres (SI) ó (NO)."));
						}

//						#ANIO
						nominaUser.setAnio(anio);

//						#MES
						nominaUser.setMes(mes);

//						#RECTIFICATIVA
						int valor_rectificativa = Integer.parseInt(formatter.formatCellValue(row.getCell(11)));
						int rectificativa_db = srvObtenerelUltimoRectificativo(validEmpresa.get().getIdEmpresa(), anio,
								mes);

						if (rectificativa_db == valor_rectificativa) {
							nominaUser.setRectificativa(valor_rectificativa);
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "RECTIFICATIVA",
									"El valor del campo no corresponde al numero consecutivo: DICE ("
											+ valor_rectificativa + ") <====>  DEBE DECIR (" + rectificativa_db + ")"));
						}

//						#MONTO SAC
						String monto_sac = formatter.formatCellValue(row.getCell(14));
						Matcher matcherMontoSac = paterNumericDecimales.matcher(monto_sac);
//						#VALIDAR SI CUMPLE LA EXPRECION REGULAR
						if (matcherMontoSac.matches()) {
							XSSFCell montoSacCell = (XSSFCell) row.getCell(14);
							BigDecimal monto_sac_decimal = new BigDecimal(montoSacCell.getNumericCellValue())
									.setScale(2, BigDecimal.ROUND_HALF_DOWN);
							nominaUser.setMontoSac(monto_sac_decimal.doubleValue());
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "MONTO SAC", "El valor del campo ("
									+ monto_sac
									+ ") no cumple con el formato adecuado. Ejemplo del formato (000000.00), sin simbolos y con 2 decimales."));
						}

//						#LICENCIA
						String licencia = formatter.formatCellValue(row.getCell(12));
						if (!licencia.isEmpty()) {
							if (licencia.toUpperCase().compareTo("S") == 0) {
								nominaUser.setLicencia(true);
							} else if (licencia.toUpperCase().compareTo("N") == 0) {
								nominaUser.setLicencia(false);
							} else {
								errorCount++;
								logErrores.add(estructuraLogCargaMasiva(contadorRow, "LICENCIAL",
										"El valor del campo solo puede contener 1 valor (S) ó (N)"));
							}
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(errorCount, "LICENCIAL",
									"El valor del campo es requerido y solo puede contener 1 valor (S) ó (N)"));
						}

//						#AFILIADO OBRA SOCIAL
						String afiliado_obra_social = formatter.formatCellValue(row.getCell(19));
						if (!afiliado_obra_social.isEmpty()) {
							if (afiliado_obra_social.toUpperCase().compareTo("SI") == 0) {
								nominaUser.setAfiliadoObraSocial(true);
							} else if (afiliado_obra_social.toUpperCase().compareTo("NO") == 0) {
								nominaUser.setAfiliadoObraSocial(false);
							} else {
								errorCount++;
								logErrores.add(estructuraLogCargaMasiva(contadorRow, "AFILIADO OBRA SOCIAL",
										"El valor del campo solo puede contener 2 valores (SI) ó (NO)"));
							}
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "AFILIADO OBRA SOCIAL",
									"El valor del campo es requerido y no puede estar vacio. Solo puede contener 2 valores (SI) ó (NO)"));
						}

//						#OBSERVACIONES
						String observaciones = formatter.formatCellValue(row.getCell(20));
						Matcher matcherObservaciones = paterLetras.matcher(observaciones.replace(" ", ""));
						if (!observaciones.isEmpty()) {
							if (matcherObservaciones.matches()) {
								nominaUser.setObservaciones(observaciones);
							} else {
								logErrores.add(estructuraLogCargaMasiva(contadorRow, "OBSERVACIONES",
										"El valor del campo solo debe de contener letras."));
							}
						}

//						#CANTIDAD DIAS TRABAJADOS
						String cant_dias_trabjados = formatter.formatCellValue(row.getCell(13));
						Pattern paterNumeric = Pattern.compile(Constantes.EXPRESION_REGULAR_SOLO_NUMEROS);
						Matcher matcherCantidad_dias_trabajados = paterNumeric.matcher(cant_dias_trabjados);

						if (matcherCantidad_dias_trabajados.matches()) {
							nominaUser.setCantidadDiasTrabajados(Integer.parseInt(cant_dias_trabjados));
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "CANTIDAD DIAS TRABAJADOS",
									"El valor del campo es requerido y no puede estar vacio y solo puede contener valores numericos."));
						}

//						#SINDICATO
						String sindicato_nombre = formatter.formatCellValue(row.getCell(8)).trim();
						Optional<SindicatosModel> validSindicato = repoSindicato
								.findByNombreSindicato(sindicato_nombre);
						if (validSindicato.isPresent()) {
							nominaUser.setSindicato(validSindicato.get());
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "SINDICATO",
									"El valor del campo no coincide con ningun SINDICATO registrado, es posible que este mal digitado ó vacio."));
						}

//						#ZONA
						String zona = formatter.formatCellValue(row.getCell(15)).trim();
						Optional<ZonasModel> validZona = repoZona.findByZona(zona);
						if (validZona.isPresent()) {
							nominaUser.setZona(validZona.get());
						} else {
							errorCount++;
							logErrores.add(estructuraLogCargaMasiva(contadorRow, "ZONA",
									"El valor del campo no se encuentra registrado ó es posible que el campo esta vacio ó mal escrito, se solicita su correccion"));
						}

//						#LLENAR TODO LOS DATOS AL ARRAY PARA EL GUARDADO AL FINAL
						listNomina.add(nominaUser);
					} else {
						errorCount++;
						logErrores.add(estructuraLogCargaMasiva(contadorRow, "CUIT",
								"El valor del campo no coincide con ninguna empresa registrada, es posible que el CUIT este mal digitado ó vacio."));
					}

				}
			}

			workbook.close();
//			#================== DESPUES DE VALIDAR Y TENER TODO LA DATA PROCEDEMOS A REALIZAR EL GUARDADO DE DICHA INFORMACION ======================
//			#VALIDAR QUE NO TENGA NINGUN ERROR AL GUARDAR
			if (errorCount == 0) {
				repoDeclarados.saveAll(listNomina);
				map.put("message", "La data fue procesada y registrada con éxito");
				map.put("errores", errorCount);
			} else {
//				#CREAR ARCHIVO LOG 
				String nameLog = "LOG" + id_empresa + anio + mes + rectificativa + ".txt";
				FileWriter logMasivo = new FileWriter("src//main//webapp//temp//" + nameLog);
				for (String error : logErrores) {
					logMasivo.write(error);
				}
				logMasivo.close();
				String url = MvcUriComponentsBuilder.fromMethodName(StorageController.class, "getFile", nameLog).build()
						.toString();
				map.put("message", "El archivo contiene muchos errores se solicita su correccion");
				map.put("errores", errorCount);
				map.put("log", url);

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("ERROR GUARDAR MASIVO NOMINA => " + e.toString());
		} finally {
			if (fileExcelMasivo.exists()) {
				fileExcelMasivo.delete();
			}

		}
		return map;
	}

	@Override
	public int srvObtenerelUltimoRectificativo(int id_empresa, String anio, String mes) {
		int returValor = 0;
		try {
			if (repoDeclarados.findContarItemRectificativaEmpresaAnioMes(id_empresa, anio, mes) > 0) {
				returValor = jdbcTemplate
						.queryForObject(
								"SELECT (rectificativa + 1 ) VALOR FROM tb_nominas WHERE id_empresa = " + id_empresa
										+ " AND anio = '" + anio + "' AND mes = '" + mes
										+ "' GROUP BY rectificativa, id_nomina ORDER BY id_nomina DESC LIMIT 1",
								Integer.class);
			}

		} catch (Exception e) {
			log.error("ERROR AL OBTENER EL ULTIMO RECTIFICATIVO => " + e.toString());
			throw e;
		}

		return returValor;
	}

	public String estructuraLogCargaMasiva(int numero_fila, String name_columna, String message_log) {
		String estructuraLogFila = "";
		estructuraLogFila += "============== ERROR EN LA FILA (" + numero_fila
				+ ")=======================================\n";
		estructuraLogFila += "==> NOMBRE DEL CAMPO: " + name_columna + "\n";
		estructuraLogFila += "==> OBSERVACION: " + message_log + "\n";
		estructuraLogFila += "=============================================================================================\n";
		return estructuraLogFila;
	}

}
