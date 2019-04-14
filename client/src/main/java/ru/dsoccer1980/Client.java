package ru.dsoccer1980;

import ru.dsoccer1980.domain.Command;
import ru.dsoccer1980.drawing.Frame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;

public class Client extends Thread {

    private final static String HOST = "localhost";
    private final static int PORT = 29288;
    private static Socket clientSocket;
    private static BufferedReader in;

    Frame frame;

    public Client(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        createConnection();
    }

    private void createConnection() {
        try {
            try {

                clientSocket = new Socket(HOST, PORT);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String stringFromServer;
                Queue<Command> queue = new ArrayDeque<>();

                while (!in.toString().equals("exit")) {
                    stringFromServer = in.readLine();

                    String[] record = stringFromServer.split(";");
                    if (hasRightFormat(record)) {
                        queue.add(new Command(record[0], record[1], record[2], record[3], record[4]));
                    }

                    if (queue.size() == 3) {
                        Command command = queue.poll();
                        float x1 = Float.valueOf(command.getPointX());
                        float y1 = Float.valueOf(command.getPointY());
                        command = queue.peek();
                        float x2 = Float.valueOf(command.getPointX());
                        float y2 = Float.valueOf(command.getPointY());
                        command = queue.peek();
                        float x3 = Float.valueOf(command.getPointX());
                        float y3 = Float.valueOf(command.getPointY());
                        frame.paintCurve(x1, y1, x2, y2, x3, y3);
                    }

                    System.out.println(stringFromServer);
                }
            } finally {
                clientSocket.close();
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //TODO проверить формат
    private boolean hasRightFormat(String[] record) {
        return record.length == 5;
    }

}
