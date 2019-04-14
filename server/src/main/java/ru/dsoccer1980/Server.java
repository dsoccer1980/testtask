package ru.dsoccer1980;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Server {

    private final int PORT = 29288;
    private final String FILE_NAME = "test.txt";

    public static void main(String[] args) {
        new Server().createConnection();
    }

    private void createConnection() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started");

            try (Socket clientSocket = server.accept();
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                System.out.println("Client connected");

                try (InputStream inputStream = Objects.requireNonNull(
                        Server.class.getClassLoader().getResourceAsStream(FILE_NAME), "File not found");
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                    while (!clientSocket.isClosed()) {
                        String command;
                        while ((command = reader.readLine()) != null) {
                            out.write(command + "\n");
                            out.flush();
                            delay();
                        }

                        out.write(" ");
                        out.flush();

                    }
                } catch (SocketException e) {
                }
            } finally {
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            System.out.println("Server shutdown");
        }

    }

    private void delay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}