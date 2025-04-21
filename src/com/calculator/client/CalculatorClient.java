package com.calculator.client;

import com.calculator.common.Protocol;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalculatorClient {
    public static void main(String[] args) {
        String servidor = obtenerServidor(args);
        int puerto = obtenerPuerto(args);

        try (Socket socket = new Socket(servidor, puerto);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            mostrarInstrucciones();
            manejarEntradaUsuario(salida, entrada, teclado);

        } catch (UnknownHostException e) {
            System.err.println("❌ No se encontró el servidor: " + servidor);
        } catch (IOException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }
    }

    private static String obtenerServidor(String[] args) {
        return args.length > 0 ? args[0] : Protocol.DEFAULT_HOST;
    }

    private static int obtenerPuerto(String[] args) {
        return args.length > 1 ? Integer.parseInt(args[1]) : Protocol.DEFAULT_PORT;
    }

    private static void mostrarInstrucciones() {
        System.out.println("\n🟢 Conectado al servidor de calculadora");
        System.out.println("📝 Instrucciones:");
        System.out.println("   - Formato: OPERACIÓN NUM1 NUM2");
        System.out.println("   - Operaciones válidas: sumar, restar, multiplicar, dividir");
        System.out.println("   - Ejemplos:");
        System.out.println("     sumar 5 3.2");
        System.out.println("     dividir 10 4");
        System.out.println("   - Escribe 'salir' para terminar\n");
    }

    private static void manejarEntradaUsuario(PrintWriter salida,
            BufferedReader entrada,
            BufferedReader teclado) throws IOException {
        String comando;
        while ((comando = teclado.readLine()) != null) {
            if (comando.equalsIgnoreCase(Protocol.EXIT_COMMAND)) {
                System.out.println("👋 Saliendo del programa...");
                break;
            }

            if (!validarFormato(comando))
                continue;

            String solicitud = comando.replace(" ", Protocol.DELIMITER);
            salida.println(solicitud);

            String respuesta = entrada.readLine();
            System.out.println("🔍 Resultado: " + respuesta);
        }
    }

    private static boolean validarFormato(String comando) {
        String[] partes = comando.split(" ");
        if (partes.length != 3) {
            System.out.println(Protocol.INVALID_FORMAT_ERROR.replace(",", " "));
            return false;
        }

        if (!partes[0].matches("(?i)sumar|restar|multiplicar|dividir")) {
            System.out.println(Protocol.INVALID_OPERATION_ERROR);
            return false;
        }

        try {
            Double.parseDouble(partes[1]);
            Double.parseDouble(partes[2]);
        } catch (NumberFormatException e) {
            System.out.println(Protocol.INVALID_NUMBER_ERROR);
            return false;
        }
        return true;
    }
}