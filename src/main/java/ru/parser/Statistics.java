package ru.parser;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private List<LogEntry> logEntries;
    private Set<String> existingPages;
    private Map<String, Integer> osFrequency;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        this.logEntries = new ArrayList<>();
        this.existingPages = new HashSet<>();
        this.osFrequency = new HashMap<>();
    }

    public void addEntry(LogEntry entry) {
        totalTraffic += entry.getResponseSize();

        if (minTime == null || entry.getDateTime().isBefore(minTime)) {
            minTime = entry.getDateTime();
        }

        if (maxTime == null || entry.getDateTime().isAfter(maxTime)) {
            maxTime = entry.getDateTime();
        }

        if (entry.getResponseCode() == 200) {
            existingPages.add(entry.getRequestPath());
        }

        String os = entry.getUserAgent().getOperatingSystem();
        osFrequency.put(os, osFrequency.getOrDefault(os, 0) + 1);

        logEntries.add(entry);
    }

    public double getTrafficRate() {
        if (minTime == null || maxTime == null || minTime.equals(maxTime)) {
            return 0;
        }

        long hours = Duration.between(minTime, maxTime).toHours();
        long minutes = Duration.between(minTime, maxTime).toMinutes();

        return hours > 0 ? (double) totalTraffic / hours : (double) totalTraffic * 60 / minutes;
    }

    public long getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public Set<String> getExistingPages() {
        return existingPages;
    }

    public Map<String, Double> getOsStatistics() {
        Map<String, Double> osStatistics = new HashMap<>();
        int totalEntries = logEntries.size();

        for (Map.Entry<String, Integer> entry : osFrequency.entrySet()) {
            osStatistics.put(entry.getKey(), (double) entry.getValue() / totalEntries);
        }

        return osStatistics;
    }

    public double calculateAverageTrafficPerHour() {
        if (totalTraffic <= 0 || minTime == null || maxTime == null) {
            return 0;
        }

        long durationInHours = Duration.between(minTime, maxTime).toHours();
        if (durationInHours <= 0) {
            return 0;
        }

        return (double) totalTraffic / durationInHours;
    }

    public void printStatistics() {
        System.out.println("4) Список существующих страниц: " + existingPages.size());

        System.out.println("5) Статистика операционных систем:");
        Map<String, Double> osStatistics = getOsStatistics();
        for (Map.Entry<String, Double> entry : osStatistics.entrySet()) {
            System.out.printf("%s: %.2f%%%n", entry.getKey(), entry.getValue() * 100);
        }
    }
}


