package com.calculator.server;

import com.calculator.common.Protocol;
import com.calculator.server.service.CalculatorService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final CalculatorService calculadora = new CalculatorService();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("✅ Nueva conexión: " + socket.getInetAddress());

            String solicitud;
            while ((solicitud = entrada.readLine()) != null) {
                String respuesta = calculadora.procesar(solicitud);
                salida.println(respuesta);
            }
        } catch (IOException e) {
            System.out.println("⚠️  Error con cliente: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    private void cerrarConexion() {
        try {
            socket.close();
            System.out.println("❌ Conexión cerrada: " + socket.getInetAddress());
        } catch (IOException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}