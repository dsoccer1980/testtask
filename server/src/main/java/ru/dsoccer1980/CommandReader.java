package ru.dsoccer1980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CommandReader extends Thread {
    private final String FILE_NAME = "test.txt";

    @Override
    public void run() {
        readCommand();
    }

    private void readCommand() {
        try (InputStream inputStream = Objects.requireNonNull(
                CommandReader.class.getClassLoader().getResourceAsStream(FILE_NAME), "File not found");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Server.queue.offer(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //TODO проверить формат
    private boolean hasRightFormat(String[] record) {
        return record.length == 5;
    }


}
