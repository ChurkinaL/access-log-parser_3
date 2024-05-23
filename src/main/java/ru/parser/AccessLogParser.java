package ru.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AccessLogParser {
    private static final String FILE_PATH = "src/main/resources/files/access.log";
    private static final int MAX_LINE_LENGTH = 1024;

    public void readFile() {
        int totalLines = 0;
        int googleBotCount = 0;
        int yandexBotCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                int length = line.length();

                if (length > MAX_LINE_LENGTH) {
                    throw new LineTooLongException("Длина строки превышает " + MAX_LINE_LENGTH + " фактически: " + length);
                }

                totalLines++;
                String userAgent = extractUserAgent(line);

                if (userAgent != null) {
                    String[] parts = userAgent.split(";");
                    if (parts.length >= 2) {
                        String fragment = parts[1].trim();
                        String botName = fragment.split("/")[0].trim();

                        if (botName.equals("Googlebot")) {
                            googleBotCount++;
                        } else if (botName.equals("YandexBot")) {
                            yandexBotCount++;
                        }
                    }
                }
            }

            double googleBotPercentage = (googleBotCount * 100.0) / totalLines;
            double yandexBotPercentage = (yandexBotCount * 100.0) / totalLines;

            System.out.printf("Googlebot запросы: %.2f%%\n", googleBotPercentage);
            System.out.printf("YandexBot запросы: %.2f%%\n", yandexBotPercentage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractUserAgent(String line) {
        int startIndex = line.indexOf("(");
        int endIndex = line.indexOf(")");

        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            return line.substring(startIndex + 1, endIndex);
        }
        return null;
    }
    /**
     * Отладка
     */
    public static void main(String[] args) {
        AccessLogParser accessLogParser = new AccessLogParser();
        accessLogParser.readFile();
    }
}