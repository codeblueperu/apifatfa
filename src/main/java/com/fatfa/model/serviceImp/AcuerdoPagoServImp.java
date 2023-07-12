package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.model.dto.DetalleCuadroCuotasDTO;
import com.fatfa.model.entity.AcuerdosPagoModel;
import com.fatfa.model.repository.IAcuerdoPagoDao;
import com.fatfa.model.service.IAcuerdoPagoService;
import com.fatfa.utils.Constantes;

@Service
public class AcuerdoPagoServImp implements IAcuerdoPagoService {
	private static final Logger log = LoggerFactory.getLogger(AcuerdoPagoServImp.class);

	@Autowired
	private IAcuerdoPagoDao repoAcuerdo;

	@Override
	public List<AcuerdosPagoModel> srvListaAcuerdosPago(int idEmpresa) {
		// TODO Auto-generated method stub
		return repoAcuerdo.findAll();
	}

	@Override
	public HashMap<String, Object> srvCalcularDetalleCuotas(float capitalInicial, float tasainteres, int ncuotas) {

		HashMap<String, Object> response = new HashMap<>();
		try {
//			CALCULO DE CUOTA PURO
			float cuota_pura = capitalInicial / ncuotas;
			float valorResidual = capitalInicial;
			List<DetalleCuadroCuotasDTO> detalle = new ArrayList<>();
		
			// System.err.println( cuota_pura );

			for (int i = 1; i <= ncuotas; i++) {
				float interesCuota = valorResidual * (float) tasainteres / 100;
				float cuotaTotalConInteres = interesCuota + cuota_pura;
				float valorCuotaP = cuotaTotalConInteres - interesCuota;

				DetalleCuadroCuotasDTO cuadro = new DetalleCuadroCuotasDTO();
				cuadro.setNumCuota(i);
				cuadro.setValorResidual(Constantes.formatearDecimales(valorResidual, 2));
				cuadro.setInteres(Constantes.formatearDecimales(interesCuota,3));
				cuadro.setCuotaP(Constantes.formatearDecimales(valorCuotaP, 2));
				cuadro.setCuota(Constantes.formatearDecimales(cuota_pura, 2));
				cuadro.setCuotaTotalConIntereses(cuotaTotalConInteres);

				valorResidual = valorResidual - valorCuotaP;
				detalle.add(cuadro);
			}

			double total_intereses = detalle.stream().mapToDouble(i -> i.getInteres()).sum();
			double total_pagar = detalle.stream().mapToDouble(c -> c.getCuotaTotalConIntereses()).sum();
			float valor_por_cuotas = (float) total_pagar / ncuotas;

			response.put("valor_por_cuota", Constantes.formatearDecimales(valor_por_cuotas, 2));
			response.put("cuota_pura", Constantes.formatearDecimales(cuota_pura, 2));
			response.put("monto_total_interes", Constantes.formatearDecimales((float)total_intereses, 2));
			response.put("monto_total_pagar", Constantes.formatearDecimales((float)total_pagar, 2));
			response.put("detalle", detalle);
		} catch (Exception e) {
			log.error("Error al calcular detalle cuotas => " + e.toString());

			throw e;
		}
		return response;
	}

}
