package com.calculator.server.service;

import com.calculator.common.Protocol;

public class CalculatorService {
    public String procesar(String entrada) {
        String[] partes = entrada.split(Protocol.DELIMITER);
        if (partes.length != 3) return Protocol.INVALID_FORMAT_ERROR;
        
        try {
            double num1 = Double.parseDouble(partes[1].trim());
            double num2 = Double.parseDouble(partes[2].trim());
            
            return switch (partes[0].toLowerCase()) {
                case "sumar" -> String.format("%.2f", num1 + num2);
                case "restar" -> String.format("%.2f", num1 - num2);
                case "multiplicar" -> String.format("%.2f", num1 * num2);
                case "dividir" -> num2 == 0 ? Protocol.DIVISION_BY_ZERO_ERROR : String.format("%.2f", num1 / num2);
                default -> Protocol.INVALID_OPERATION_ERROR;
            };
        } catch (NumberFormatException e) {
            return Protocol.INVALID_NUMBER_ERROR;
        }
    }
}