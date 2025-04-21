package com.calculator.common;

public class Protocol {
    public static final String DELIMITER = ",";
    public static final String EXIT_COMMAND = "salir";
    public static final String INVALID_FORMAT_ERROR = "ERROR: Formato incorrecto. Ejemplo: sumar,5,3";
    public static final String INVALID_OPERATION_ERROR = "ERROR: Operación no válida (usar: sumar, restar, multiplicar, dividir)";
    public static final String INVALID_NUMBER_ERROR = "ERROR: Valores numéricos inválidos";
    public static final String DIVISION_BY_ZERO_ERROR = "ERROR: División por cero no permitida";
    public static final int DEFAULT_PORT = 12345;
    public static final String DEFAULT_HOST = "localhost";
}