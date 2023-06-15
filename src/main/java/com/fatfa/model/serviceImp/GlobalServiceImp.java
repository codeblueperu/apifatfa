package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fatfa.model.dto.MatrizPeriodoIntersDTO;
import com.fatfa.model.entity.MatrizInterecesModel;
import com.fatfa.model.repository.IMatrizPagoInteresRepository;
import com.fatfa.model.service.IGlobalService;
import com.fatfa.utils.Constantes;

@Service
public class GlobalServiceImp implements IGlobalService {

	private static final Logger log = LoggerFactory.getLogger(GlobalServiceImp.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IMatrizPagoInteresRepository repoMatriz;

	@Override
	public int onCalcularDiferenciaFechas(String fechaInio, String fechaVencimiento) {
		int diaTranscurridos = 0;
		try {
			diaTranscurridos = jdbcTemplate.queryForObject(
					"SELECT TIMESTAMPDIFF(DAY, '" + fechaInio + "','" + fechaVencimiento + "') FROM dual",
					Integer.class);

			diaTranscurridos++;
		} catch (Exception e) {
			log.error("ERROR CALCULAR DIFERENCIA FECHA => " + e.toString());
			throw e;
		}
		return diaTranscurridos;
	}

	@Override
	public double onObetenerValorInteres(int tipoInteres, String fechaInicioAAAAMM, String fechaFinAAAAMM) {
//		#SEGUN EL TIPO DE INTERES
		String campoObtener = "";

//		#INTERES MENSUAL
		if (tipoInteres == 1) {
			campoObtener = "interes_mensual";
		} else {
			campoObtener = "interes_diario";
		}

		return jdbcTemplate.queryForObject("SELECT " + campoObtener
				+ " FROM tb_matriz_intereces where date_format(fecha_inicio, '%Y-%m') BETWEEN  '"+fechaInicioAAAAMM+"' AND '"+fechaFinAAAAMM+"' ORDER BY date_format(fecha_inicio, '%Y-%m') DESC LIMIT 1;",
				Double.class);
	}

	@Override
	public double onObtenerRemuneracionIntegral(int idNomina, double sueldoBasico) {
		return jdbcTemplate.queryForObject(
				"SELECT calcular_coeficiente_antiguedad(id_nomina,id_empresa,anio,mes,sueldo) FROM tb_nominas WHERE id_nomina = "
						+ idNomina,
				Double.class);
	}

	@Override
	public List<MatrizPeriodoIntersDTO> onObtenerMatrizPerido() {
		List<MatrizPeriodoIntersDTO> listaMatriz = new ArrayList<>();
		try {
			List<MatrizInterecesModel>  listaDataMatriz=	repoMatriz.findAll();
			
			for (MatrizInterecesModel matrizDB : listaDataMatriz) {
				String anioInicio = Constantes.utilFormatoFecha(matrizDB.getFechaInicio(), "yyyy");
				String mesInicio = Constantes.utilFormatoFecha(matrizDB.getFechaInicio(), "MM");
				String anioTermino = Constantes.utilFormatoFecha(matrizDB.getFechaTermino(), "yyyy");
				String mesTermino = Constantes.utilFormatoFecha(matrizDB.getFechaTermino(), "MM");
				
				String stringPeriodo = anioTermino + mesTermino;
				for (int i = Integer.parseInt(anioInicio); i <= Integer.parseInt(anioTermino); i++) {
					int mesNumber = 0;
					if(i >= 2023) {
						mesNumber = 1;
					}else {
						mesNumber = Integer.parseInt(mesInicio);
					}
					for (int j = mesNumber; j <= 12; j++) {
						//System.err.println(i+"-"+Constantes.completeCeroIzquierda(String.valueOf(j), 2) +  "- " + matrizDB.getInteresDiario() +  " - " + matrizDB.getInteresMensual());
						MatrizPeriodoIntersDTO itemmatriz = new MatrizPeriodoIntersDTO();
						itemmatriz.setPeriodo(i+"-"+Constantes.completeCeroIzquierda(String.valueOf(j), 2));
						itemmatriz.setInteresDiario(matrizDB.getInteresDiario());
						itemmatriz.setInteresMensual(matrizDB.getInteresMensual());
						listaMatriz.add(itemmatriz);
						if(stringPeriodo.compareTo(i + Constantes.completeCeroIzquierda(String.valueOf(j), 2)) == 0) {
							break;
						}
					}
				}
				
			}
			//System.err.println(listaMatriz);
		} catch (Exception e) {
			log.error("ERROR AL OBTENER MATRIZ => " + e.toString());
			throw e;
		}
		return listaMatriz;
	}

}
