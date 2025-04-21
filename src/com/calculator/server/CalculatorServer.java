package com.calculator.server;

import com.calculator.common.Protocol;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CalculatorServer {
    private static final int MAX_THREADS = 10;

    public static void main(String[] args) {
        int port = obtenerPuerto(args);
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            apagarServidor(executor);
            System.out.println("\n🔴 Servidor detenido");
        }));

        iniciarServidor(port, executor);
    }

    private static int obtenerPuerto(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Puerto inválido. Usando puerto por defecto: " + Protocol.DEFAULT_PORT);
            }
        }
        return Protocol.DEFAULT_PORT;
    }

    private static void apagarServidor(ExecutorService executor) {
        System.out.println("\n⏳ Apagando servidor...");
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    private static void iniciarServidor(int port, ExecutorService executor) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("🟢 Servidor de calculadora iniciado");
            System.out.println("🔌 Puerto: " + port);
            System.out.println("💡 Presiona Ctrl + C para detener el servidor\n");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("❌ Error al iniciar servidor: " + e.getMessage());
        }
    }
}