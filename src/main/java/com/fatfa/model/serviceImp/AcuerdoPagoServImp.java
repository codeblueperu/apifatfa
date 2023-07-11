package com.fatfa.model.serviceImp;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	public HashMap<String, Object> srvCalcularDetalleCuotas(double capitalInicial, double tasainteres, int ncuotas) {

		HashMap<String, Object> response = new HashMap<>();
		try {
//			CALCULO DE CUOTA PURO
			double cuota_pura = Constantes.formatearDecimales(capitalInicial, 3) / ncuotas;
			double valorResidual = capitalInicial;
			List<DetalleCuadroCuotasDTO> detalle = new ArrayList<>();

			// System.err.println(Constantes.formatearDecimales(cuota_pura, 3));

			for (int i = 1; i <= ncuotas; i++) {

				double interesCuota = Constantes.formatearDecimales(valorResidual, 3)
						* Constantes.formatearDecimales(tasainteres / 100, 3);
				double cuotaTotalConInteres = Constantes.formatearDecimales(interesCuota, 3)
						+ Constantes.formatearDecimales(cuota_pura, 3);
				double valorCuotaP = Constantes.formatearDecimales(cuotaTotalConInteres, 3)
						- Constantes.formatearDecimales(interesCuota, 3);

				DetalleCuadroCuotasDTO cuadro = new DetalleCuadroCuotasDTO();
				cuadro.setNumCuota(i);
				cuadro.setValorResidual( Constantes.formatearDecimales(valorResidual,2));
				cuadro.setInteres( Constantes.formatearDecimales(interesCuota,2));
				cuadro.setCuotaP( Constantes.formatearDecimales(valorCuotaP,2));
				cuadro.setCuota( Constantes.formatearDecimales(cuota_pura,2));
				cuadro.setCuotaTotalConIntereses(Constantes.formatearDecimales(cuotaTotalConInteres, 2));
				
				valorResidual = Constantes.formatearDecimales(valorResidual, 3)
						- Constantes.formatearDecimales(valorCuotaP, 3);
				detalle.add(cuadro);
			}

			double total_intereses = detalle.stream().mapToDouble(i -> i.getInteres()).sum();
			double total_pagar = detalle.stream().mapToDouble(c -> c.getCuotaTotalConIntereses()).sum();
			double valor_por_cuotas = Constantes.formatearDecimales(total_pagar / ncuotas, 3);

			response.put("valor_por_cuota",  Constantes.formatearDecimales(valor_por_cuotas,2));
			response.put("cuota_pura",  Constantes.formatearDecimales(cuota_pura,2));
			response.put("monto_total_interes", total_intereses);
			response.put("monto_total_pagar", total_pagar);
			response.put("detalle", detalle);
		} catch (Exception e) {
			log.error("Error al calcular detalle cuotas => " + e.toString());

			throw e;
		}
		return response;
	}

}
