package ru.dsoccer1980;

import ru.dsoccer1980.drawing.Frame;
import ru.dsoccer1980.model.Command;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;

public class Client extends Thread {

    private final String HOST = "localhost";
    private final int PORT = 29288;
    private final Color DEFAULT_COLOR = Color.black;
    private Frame frame;

    public Client() {
        frame = new Frame(this);
    }

    public static void main(String[] args) {
        new Client();
    }

    @Override
    public void run() {
        createConnection();
    }

    private void createConnection() {
        try (Socket clientSocket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String stringFromServer;
            Queue<Command> commandQueue = new ArrayDeque<>();

            while (clientSocket.isConnected()) {
                stringFromServer = in.readLine();
                String[] record = stringFromServer.split(";");

                if (hasRightFormat(record)) {
                    if ("start".equals(record[1])) {
                        commandQueue.clear();
                    }
                    commandQueue.add(new Command(record[0], record[1], record[2], record[3], record[4]));
                }

                if (commandQueue.size() == 3) {
                    getValuesAndPaintCurve(commandQueue);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void getValuesAndPaintCurve(Queue<Command> commandQueue) {
        Command command = commandQueue.poll();
        float x1 = Float.valueOf(command.getPointX());
        float y1 = Float.valueOf(command.getPointY());
        command = commandQueue.peek();
        float x2 = Float.valueOf(command.getPointX());
        float y2 = Float.valueOf(command.getPointY());
        command = commandQueue.peek();
        float x3 = Float.valueOf(command.getPointX());
        float y3 = Float.valueOf(command.getPointY());
        Color color = DEFAULT_COLOR;
        if (Integer.valueOf(command.getColor()) == -16777216) {
            color = Color.black;
        }

        frame.paintCurve(x1, y1, x2, y2, x3, y3, color);
    }

    private boolean hasRightFormat(String[] record) {
        if (record.length != 5) {
            return false;
        }
        return "start".equals(record[1]) || "move".equals(record[1]);
    }


}
