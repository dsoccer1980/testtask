package ru.dsoccer1980;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Server {

    private final static int PORT = 29288;
    public static ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque<>();
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedWriter out;

    public static void main(String[] args) {
        new Server().createConnection();
    }

    private void createConnection() {
        try {
            try {
                server = new ServerSocket(PORT);
                System.out.println("Сервер запущен!");
                clientSocket = server.accept();
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                new CommandReader().start();
                try {
                    while (clientSocket.isConnected()) {
                        String command = Server.queue.poll();
                        if (command != null) {
                            out.write(command + "\n");
                            out.flush();
                            System.out.println(command);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } finally {
                    clientSocket.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}