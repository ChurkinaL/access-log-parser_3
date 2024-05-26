package ru.parser;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private List<LogEntry> logEntries;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        this.logEntries = new ArrayList<>();
    }

    public void addEntry(LogEntry entry) {
        totalTraffic += entry.getResponseSize();

        if (minTime == null || entry.getDateTime().isBefore(minTime)) {
            minTime = entry.getDateTime();
        }

        if (maxTime == null || entry.getDateTime().isAfter(maxTime)) {
            maxTime = entry.getDateTime();
        }
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
}
