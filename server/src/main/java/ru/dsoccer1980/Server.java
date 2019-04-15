package ru.dsoccer1980;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class Server {

    private final int PORT = 29288;
    private final String DEFAULT_FILE_NAME = "test.txt";
    private String fileName;

    public Server() {
        fileName = DEFAULT_FILE_NAME;
    }

    public Server(String fileName) {
        this.fileName = decode(fileName);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            new Server().createConnection();
        } else {
            new Server(args[0]).createConnection();
        }
    }

    private void createConnection() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started");

            try (Socket clientSocket = server.accept();
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                System.out.println("Client connected");

                try (InputStream inputStream = requireNonNullElseGet(
                        Server.class.getClassLoader().getResourceAsStream(fileName),
                        this::getInputStreamFromExternalFile);
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
                    //nothing to do
                }
            } finally {
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
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

    private InputStream getInputStreamFromExternalFile() {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    private String decode(String fileName) {
        try {
            return URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static <T> T requireNonNullElseGet(T obj, Supplier<? extends T> supplier) {
        return (obj != null) ? obj
                : requireNonNull(requireNonNull(supplier, "supplier").get(), "supplier.get()");
    }

    private static <T> T requireNonNull(T obj, String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }
}