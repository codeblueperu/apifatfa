package com.fatfa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class Constantes {

	public static final String EXPRESION_REGULAR_SOLO_LETRAS = "[a-záÁéÉìÌíÍóÓúÚñÑA-Z()¿?,.]*";
	public static final String EXPRESION_REGULAR_SOLO_NUMEROS = "[0-9]+";
	public static final String EXPRESION_REGULAR_NUMEROS_DECIMALES = "^[0-9]+([.,][0-9]+)?$";
	public static final String EXPRESION_REGULAR_FORMATO_FECHA_YYYY_MM_DD = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
	public static final String EXPRESION_REGULAR_FORMATO_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Date date = new Date();
	private static LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	public static int anioActual = localDate.getYear();
	public static int mesActual = localDate.getMonthValue();
	public static int diaActual = localDate.getDayOfMonth();
	public static final String ORIGNE_NAME_PAGO_FACIL = "PAGO FACIL               090061037FEDERACION ARGENTINA DE TRAB.DE FAR";

	public static int generarDigitoVerificador(String codigobarra) {
		int multiplicador = 1;
		int sumaValorDigitos = 0;
		for (int i = 0; i < codigobarra.length(); i++) {
			char item = codigobarra.charAt(i);
			String letra = String.valueOf(item);
//			# SECUENCIA ES LOS PRIMEROS 5 DIGITOS 1-3-5-7-9 Y LUEGO SE REPITE LA SUCESION 3-5-7-9 HASTA EL ULTIMO DIGITO
			if (i == 0) {
				sumaValorDigitos += Integer.parseInt(letra) * multiplicador;
				multiplicador += 2;
			} else {
				sumaValorDigitos += Integer.parseInt(letra) * multiplicador;
				multiplicador += 2;
			}

			if (multiplicador > 9) {
				multiplicador = 3;
			}
		}

		double nmod2 = sumaValorDigitos / 2;
		int mod10 = (int) nmod2 % 10;

		return mod10;
	}

	/**
	 * @author SOPORTE
	 * @apiNote OBETENR LA FECHA EN STRING SEGUN EL FORMATO
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static String utilFormatoFecha(Date fecha, String formato) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		return dateFormat.format(fecha);

	}

	/**
	 * @author SOPORTE
	 * @apiNote CONVERTIR DE STRING A DATE
	 * @param fecha
	 * @param formato
	 * @return
	 * @throws ParseException
	 */
	public static Date utilConvertirFecha(String fecha, String formato) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		return dateFormat.parse(fecha);

	}

	/**
	 * @author SOPORTE
	 * @apiNote COMPLETAR CON 0 A LA IZQUIERDA
	 * @param valor
	 * @param numCero
	 * @return
	 */
	public static String completeCeroIzquierda(String valor, int numCero) {
		return StringUtils.leftPad(String.valueOf(valor), numCero, "0");

	}
}
