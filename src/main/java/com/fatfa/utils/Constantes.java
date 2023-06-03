package com.fatfa.utils;

public class Constantes {

	public static final String EXPRESION_REGULAR_SOLO_LETRAS = "[a-záÁéÉìÌíÍóÓúÚñÑA-Z()¿?,.]*";
	public static final String EXPRESION_REGULAR_SOLO_NUMEROS = "[0-9]+";
	public static final String EXPRESION_REGULAR_NUMEROS_DECIMALES = "^[0-9]+([.,][0-9]+)?$";
	public static final String EXPRESION_REGULAR_FORMATO_FECHA_YYYY_MM_DD = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
	public static final String EXPRESION_REGULAR_FORMATO_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}
