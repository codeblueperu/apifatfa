package com.fatfa.model.serviceImp;

import java.io.File;
import java.io.FileInputStream;
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

import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.entity.SindicatosModel;
import com.fatfa.model.entity.ZonasModel;
import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.CategoriasModelo;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.repository.ICategoriasRepository;
import com.fatfa.model.repository.IDeclaradosRepository;
import com.fatfa.model.repository.IEmpresaRepository;
import com.fatfa.model.repository.ISindicatoRepository;
import com.fatfa.model.repository.IZonasRepository;
import com.fatfa.model.service.IDeclaradosService;

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
		//HttpServletRequest request = new HttpServletRequest();
		File fileExcelMasivo = null;
		try {
			if (fileExcel.isEmpty()) {
				throw new ErrorNotFoundException(
						"Para continuar con e proceso se solicita un archivo con la extension (.xlsx).");
			}

			byte[] bytes = fileExcel.getBytes();
			Path path = Paths.get("src//main//webapp//temp//" + fileExcel.getOriginalFilename());
			Files.write(path, bytes);
			
			fileExcelMasivo = new File("src//main//webapp//temp//" +fileExcel.getOriginalFilename());
			FileInputStream inputStream = new FileInputStream(fileExcelMasivo);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);

			DataFormatter formatter = new DataFormatter();
			List<NominasModel> listNomina = new ArrayList<>();

//			#CONTADOR DE FILAS
			int contadorRow = 1;
//			#CONTADOR DE ERRORES
			int errorCount = 0;
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
							log.error("El valor del campo CUIL del trabajador en la Fila (" + contadorRow
									+ ") no puede ser vacio y solo debe de tener 11 caracteres. se solicita su correccion");
						}

//						#NOMBRE DEL TRABAJADOR
						nominaUser.setNombres(formatter.formatCellValue(row.getCell(3)));

//						#FECHA DE INGRESO
						Pattern paterFecha = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
						Matcher matcherFechaIngreso = paterFecha.matcher(formatter.formatCellValue(row.getCell(16)));
						if (matcherFechaIngreso.matches()) {
							nominaUser.setFechaIngreso(formatoFecha.parse(formatter.formatCellValue(row.getCell(16))));
						} else {
							errorCount++;
							log.error("La FECHA INGRESO " + formatter.formatCellValue(row.getCell(16))
									+ "  en la Fila (" + contadorRow
									+ ") ==> No coincide con el formato solicitado (yyyy-MM-dd) <===> (2023-01-30).");
						}

//						#CATEGORIA
						Optional<CategoriasModelo> validcategoria = repoCategoria
								.findByCategoria(formatter.formatCellValue(row.getCell(4)).toUpperCase());
						if (validcategoria.isPresent()) {
							nominaUser.setCategoria(new CategoriasModelo(validcategoria.get().getIdCategoria()));
						} else {
							errorCount++;
							log.error("El valor del campo CATEGORIA  " + formatter.formatCellValue(row.getCell(4))
									+ "  en la Fila (" + contadorRow
									+ ") ==> No coincide con ninguna categoria registrada, es posible que la CATEGORIA este mal digitado  y/ó vacio.");
						}

//						#SUELDO
						Pattern paterNumericDecimales = Pattern.compile("^[0-9]+([.,][0-9]+)?$");
						System.err.println(formatter.formatCellValue(row.getCell(5)));
						Matcher mat = paterNumericDecimales.matcher(formatter.formatCellValue(row.getCell(5)));
//						#VALIDAR SI CUMPLE LA EXPRECION REGULAR
						if (mat.matches()) {
							XSSFCell sueldoCell = (XSSFCell) row.getCell(5);
							BigDecimal sueldo_trabajador = new BigDecimal(sueldoCell.getNumericCellValue()).setScale(2,
									BigDecimal.ROUND_HALF_DOWN);
							nominaUser.setSueldo(sueldo_trabajador.doubleValue());
						} else {
							errorCount++;
							log.error("El valor del campo SUELDO no cumple con el formato adecuado en la fila (" + contadorRow
									+ ") se solicita su correccion. Ejemplo formato (00000.00), sin simbolos y con 2 decimales.");
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
										log.error("El valor del campo FECHA EGRESO " + fechaEgreso + "  en la Fila (" + contadorRow
												+ ") ==> No coincide con el formato solicitado (yyyy-MM-dd) <===> (2023-01-30).");
									}
								} else {
									errorCount++;
									log.error(
											"El valor del campo FECHA EGRESO es requerido cuando un empleado tiene el ESTADO DE BAJA (S), se solicita su correccion. en la Fila ("
													+ contadorRow + ")");
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
										log.error("El valor del campo FECHA BAJA " + fechaBaja + "  en la Fila (" + contadorRow
												+ ") ==> No coincide con el formato solicitado (yyyy-MM-dd) <===> (2023-01-30).");
									}
								} else {
									errorCount++;
									log.error(
											"El valor del campo FECHA BAJA es requerido cuando un empleado tiene el ESTADO DE BAJA (S), se solicita su correccion. en la Fila ("
													+ contadorRow + ")");
								}
								
							} else if (estadoBaja.compareTo("N") == 0) {
								nominaUser.setEstadoBaja(false);
							} else {
								errorCount++;
								log.error("El valor del campo ESTADO BAJA  en la Fila (" + contadorRow
										+ ") ==> SOLO DEBE DE TENER UN CARACTER (S) ó (N)");
							}
						} else {
							errorCount++;
							log.error("El valor del campo ESTADO BAJA  en la Fila (" + contadorRow
									+ ") ==> NO PUEDE TENER UN CARACTER DIFERENTE A (S) ó (N)");
						}

//						#FECHA PROCESA
						nominaUser.setFechaProcesa(new Date());

//						#JORNADA REDUCIDA
						if (formatter.formatCellValue(row.getCell(7)).length() == 2) {
							nominaUser.setJornadaReducida(formatter.formatCellValue(row.getCell(7)).toUpperCase());
						} else {
							errorCount++;
							log.error("EL valor del campo JORNADA REDUCIDA en la Fila (" + contadorRow
									+ ") ==> SOLO DEBE DE TENER 2 CARACTERES (SI) ó (NO)");
						}

//						#ANIO
						nominaUser.setAnio(anio);

//						#MES
						nominaUser.setMes(mes);

//						#RECTIFICATIVA
						int valor_rectificativa = Integer.parseInt(formatter.formatCellValue(row.getCell(11)));
						int rectificativa_db = srvObtenerelUltimoRectificativo(validEmpresa.get().getIdEmpresa(), anio, mes);
						System.err.println();
						if (rectificativa_db == valor_rectificativa) {
							nominaUser.setRectificativa(valor_rectificativa);
						} else {
							errorCount++;
							log.error("El valor del campo RECTIFICATIVA en la Fila (" + contadorRow
									+ ") ==> no corresponde al numero consecutivo: DICE ("
									+ valor_rectificativa + ") <=====> DEBE DECIR (" + rectificativa_db + ")");
						}

//						#MONTO SAC
						Matcher matcherMontoSac = paterNumericDecimales
								.matcher(formatter.formatCellValue(row.getCell(14)));
//						#VALIDAR SI CUMPLE LA EXPRECION REGULAR
						if (matcherMontoSac.matches()) {
							XSSFCell montoSacCell = (XSSFCell) row.getCell(14);
							BigDecimal monto_sac_decimal = new BigDecimal(montoSacCell.getNumericCellValue())
									.setScale(2, BigDecimal.ROUND_HALF_DOWN);
							nominaUser.setMontoSac(monto_sac_decimal.doubleValue());
						} else {
							errorCount++;
							log.error("EL valor del MONTO SAC " + formatter.formatCellValue(row.getCell(14))
									+ " no cumple con el formato adecuado en la fila (" + contadorRow
									+ ") se solicita su correccion. Ejemplo del formato (000000.00), sin simbolos y con 2 decimales.");
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
								log.error("El valor del campo LICENCIA en la fila (" + contadorRow
										+ ") solo acepta un solo valor (S) ó (N)");
							}
						} else {
							errorCount++;
							log.error("El valor del campo LICENCIA es requerido en la fila (" + contadorRow
									+ ") se solicita su correccion. Teniendo en cuenta que solo acepta un solo valor (S) ó (N)");
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
								log.error("El valor del campo AFILIADO OBRA SOCIAL en la fila (" + contadorRow
										+ ") solo acepta 2 valores (SI) ó (NO)");
							}
						} else {
							errorCount++;
							log.error("El valor del campo AFILIADO OBRA SOCIAL es requerido en la fila (" + contadorRow
									+ ") se solicita su correccion. Teniendo en cuenta que solo acepta 2 valores (SI) ó (NO)");
						}

//						#OBSERVACIONES
						String observaciones = formatter.formatCellValue(row.getCell(20));
						nominaUser.setObservaciones(observaciones);

//						#CANTIDAD DIAS TRABAJADOS
						String cant_dias_trabjados = formatter.formatCellValue(row.getCell(13));
						Pattern paterNumeric = Pattern.compile("[0-9]+");
						Matcher matcherCantidad_dias_trabajados = paterNumeric.matcher(cant_dias_trabjados);

						if (matcherCantidad_dias_trabajados.matches()) {
							nominaUser.setCantidadDiasTrabajados(Integer.parseInt(cant_dias_trabjados));
						} else {
							errorCount++;
							log.error(
									"EL valor del campo CANTIDAD DIAS TRABAJADOS es requerida y no puede estar vacio y solo puede contener valores numericos.");
						}

//						#SINDICATO
						String sindicato_nombre = formatter.formatCellValue(row.getCell(8)).trim();
						Optional<SindicatosModel> validSindicato = repoSindicato
								.findByNombreSindicato(sindicato_nombre);
						if (validSindicato.isPresent()) {
							nominaUser.setSindicato(validSindicato.get());
						} else {
							errorCount++;
							log.error("El valor del campo SINDICATO " + sindicato_nombre + " en la Fila (" + contadorRow
									+ ") ==> No coincide con ningun SINDICATO registrado, es posible que el Nombre este mal digitado ó vacio.");
						}

//						#ZONA
						String zona = formatter.formatCellValue(row.getCell(15)).trim();
						Optional<ZonasModel> validZona = repoZona.findByZona(zona);
						if (validZona.isPresent()) {
							nominaUser.setZona(validZona.get());
						} else {
							errorCount++;
							log.error("El valor del campo ZONA " + zona
									+ ", no se encuentra registrada o es posible que el campo esta vacio ó mal escrito, se solicita su correccion en la fila ("
									+ contadorRow + ")");
						}

//						#LLENAR TODO LOS DATOS AL ARRAY PARA EL GUARDADO AL FINAL
						listNomina.add(nominaUser);
					} else {
						errorCount++;
						log.error("El valor del campo CUIT de la empresa en la Fila (" + contadorRow
								+ " ) ==> No coincide con ninguna empresa registrada, es posible que el CUIT este mal digitado ó vacio.");
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
				map.put("message", "El archivo contiene muchos errores se solicita su correccion");
				map.put("errores", errorCount);
			}
			System.err.println(listNomina.size());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ERROR GUARDAR MASIVO NOMINA => " + e.toString());
		}finally {
			if(fileExcelMasivo.exists()) {
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

}
