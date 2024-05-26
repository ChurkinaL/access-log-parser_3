package ru.parser;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class AccessLogParser {
        private static final String FILE_PATH = "src/main/resources/files/access.log";
        private static final int MAX_LINE_LENGTH = 1024;

        public void readFile() {
                int totalLines = 0;
                int googleBotCount = 0;
                int yandexBotCount = 0;
                Statistics stats = new Statistics();

                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                        String line;

                        while ((line = reader.readLine()) != null) {
                                int length = line.length();

                                if (length > MAX_LINE_LENGTH) {
                                        throw new LineTooLongException("Длина строки превышает " + MAX_LINE_LENGTH + " фактически: " + length);
                                }

                                totalLines++;
                                LogEntry logEntry = new LogEntry(line);
                                //System.out.println(logEntry.getUserAgent().getBrowser());
                                stats.addEntry(logEntry);

                                String userAgentStr = logEntry.getUserAgent().getBrowser();
                                if ("Googlebot".equals(userAgentStr)) {
                                        googleBotCount++;
                                } else if ("YandexBot".equals(userAgentStr)) {
                                        yandexBotCount++;
                                }
                        }

                        double googleBotPercentage = (googleBotCount * 100.0) / totalLines;
                        double yandexBotPercentage = (yandexBotCount * 100.0) / totalLines;

                        System.out.printf("Googlebot запросы: %.2f%%\n", googleBotPercentage);
                        System.out.printf("YandexBot запросы: %.2f%%\n", yandexBotPercentage);
                        System.out.printf("Средний объем трафика за час: %.2f байт\n", stats.getTrafficRate());

                } catch (IOException | IllegalArgumentException e) {
                        e.printStackTrace();
                }
        }

        //* Отладка

    public static void main(String[] args) {
        AccessLogParser accessLogParser = new AccessLogParser();
        accessLogParser.readFile();
    }
}
