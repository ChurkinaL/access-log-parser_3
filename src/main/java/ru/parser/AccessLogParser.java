package ru.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AccessLogParser {
    private static final String FILE_PATH = "src/main/resources/files/access.log";
    private static final int MAX_LINE_LENGTH = 1024;

    public void readFile() {
        int lineCount = 0;
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                int length = line.length();

                if (length > MAX_LINE_LENGTH) {
                    throw new LineTooLongException("Длина строки превышает " + MAX_LINE_LENGTH + " фактически: " + length);
                }

                lineCount++;
                if (length > maxLength) {
                    maxLength = length;
                }
                if (length < minLength) {
                    minLength = length;
                }
            }

            // Обработка случая, когда файл может быть пустым
            if (lineCount == 0) {
                minLength = 0;
            }

            System.out.println("Общее количество строк: " + lineCount);
            System.out.println("Длина самой длинной линии: " + maxLength);
            System.out.println("Длина самой короткой линии: " + minLength);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    /**
     * Отладка
     */
    public static void main(String[] args) {
        AccessLogParser accessLogParser = new AccessLogParser();
        accessLogParser.readFile();
    }
}