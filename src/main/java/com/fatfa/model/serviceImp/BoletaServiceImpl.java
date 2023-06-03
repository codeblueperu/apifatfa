package com.fatfa.model.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.repository.IBoletaRepository;
import com.fatfa.model.repository.IDeclaradosRepository;
import com.fatfa.model.service.IBoletaService;

@Service
public class BoletaServiceImpl implements IBoletaService {

	private static final Logger log = LoggerFactory.getLogger(BoletaServiceImpl.class);

	@Autowired
	private IDeclaradosRepository repoNomina;

	@Autowired
	private IBoletaRepository repoBoleta;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public BoletaModel onCalcularMontoBoleta(int idEmpresa, String anio, String mes) {
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

		try {
			List<NominasModel> listaNomina = repoNomina.findByEmpresaIdEmpresaAndAnioAndMes(idEmpresa, anio, mes);

			for (NominasModel nomina : listaNomina) {
				MONTO_ART46 += nomina.getSueldo() * 1 / 100;
				MONTO_ART48 += nomina.getSueldo() * 1 / 100;
				MONTO_ART47 += onObtenerRemuneracionIntegral(nomina.getIdNomina()) * 1/100;
			}

			System.err.println(MONTO_ART46);
			System.err.println(MONTO_ART47);
			System.err.println(MONTO_ART48);

		} catch (Exception e) {
			log.error("ERROR CALCULAR BOLETA => " + e.toString());
			throw e;
		}
		return null;
	}

	@Override
	public double onObtenerRemuneracionIntegral(int idNomina) {

		return jdbcTemplate.queryForObject(
				"SELECT calcular_coeficiente_antiguedad(id_nomina,id_empresa,anio,mes,sueldo) FROM tb_nominas WHERE id_nomina = "
						+ idNomina,
				Double.class);
	}

}
