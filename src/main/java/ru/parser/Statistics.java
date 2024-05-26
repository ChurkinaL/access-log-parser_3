package ru.parser;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private List<LogEntry> logEntries;
    private Set<String> existingPages;
    private Set<String> nonExistingPages;
    private Map<String, Integer> osFrequency;
    private Map<String, Integer> browserFrequency;
    private int totalVisits;
    private int errorResponses;
    private Set<String> uniqueUserIPs;
    private int realUserVisits;
    private Map<LocalDateTime, Integer> visitsPerSecond;
    private Set<String> referringDomains;
    private Map<String, Integer> userVisitCounts;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        this.logEntries = new ArrayList<>();
        this.existingPages = new HashSet<>();
        this.nonExistingPages = new HashSet<>();
        this.osFrequency = new HashMap<>();
        this.browserFrequency = new HashMap<>();
        this.totalVisits = 0;
        this.errorResponses = 0;
        this.uniqueUserIPs = new HashSet<>();
        this.realUserVisits = 0;
        this.visitsPerSecond = new HashMap<>();
        this.referringDomains = new HashSet<>();
        this.userVisitCounts = new HashMap<>();
    }

    public void addEntry(LogEntry entry) {
        totalTraffic += entry.getResponseSize();
        totalVisits++;

        if (minTime == null || entry.getDateTime().isBefore(minTime)) {
            minTime = entry.getDateTime();
        }

        if (maxTime == null || entry.getDateTime().isAfter(maxTime)) {
            maxTime = entry.getDateTime();
        }

        if (entry.getResponseCode() == 200) {
            existingPages.add(entry.getRequestPath());
        } else if (entry.getResponseCode() == 404) {
            nonExistingPages.add(entry.getRequestPath());
        }

        if (entry.getResponseCode() >= 400) {
            errorResponses++;
        }

        String os = entry.getUserAgent().getOperatingSystem();
        osFrequency.put(os, osFrequency.getOrDefault(os, 0) + 1);

        String browser = entry.getUserAgent().getBrowser();
        browserFrequency.put(browser, browserFrequency.getOrDefault(browser, 0) + 1);

        if (!entry.getUserAgent().getBrowser().toLowerCase().contains("bot")) {
            uniqueUserIPs.add(entry.getIpAddress());
            realUserVisits++;

            LocalDateTime visitTime = entry.getDateTime().withNano(0);
            visitsPerSecond.put(visitTime, visitsPerSecond.getOrDefault(visitTime, 0) + 1);

            String referer = entry.getReferer();
            if (referer != null && !referer.isEmpty()) {
                String domain = getDomainFromReferer(referer);
                if (domain != null) {
                    referringDomains.add(domain);
                }
            }

            String ipAddress = entry.getIpAddress();
            userVisitCounts.put(ipAddress, userVisitCounts.getOrDefault(ipAddress, 0) + 1);
        }


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

    public Map<String, Double> getOsStatistics() {
        Map<String, Double> osStatistics = new HashMap<>();
        int totalEntries = logEntries.size();

        for (Map.Entry<String, Integer> entry : osFrequency.entrySet()) {
            osStatistics.put(entry.getKey(), (double) entry.getValue() / totalEntries);
        }

        return osStatistics;
    }


    private String getDomainFromReferer(String referer) {
        try {
            String[] parts = referer.split("/");
            if (parts.length > 2) {
                String domain = parts[2];
                if (domain.startsWith("www.")) {
                    domain = domain.substring(4);
                }
                return domain;
            }
        } catch (Exception e) {
            // Обработка исключения при необходимости
        }
        return null;
    }

    public Map<String, Double> getBrowserStatistics() {
        Map<String, Double> browserStatistics = new HashMap<>();
        int totalEntries = logEntries.size();

        for (Map.Entry<String, Integer> entry : browserFrequency.entrySet()) {
            browserStatistics.put(entry.getKey(), (double) entry.getValue() / totalEntries);
        }

        return browserStatistics;
    }

    public int getPeakVisitsPerSecond() {
        return visitsPerSecond.values().stream().max(Integer::compare).orElse(0);
    }

    public int getMaxVisitsPerUser() {
        return userVisitCounts.values().stream().max(Integer::compare).orElse(0);
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

    public double calculateAverageVisitsPerHour() {
        if (minTime == null || maxTime == null || minTime.equals(maxTime)) {
            return 0;
        }

        long durationInHours = Duration.between(minTime, maxTime).toHours();
        if (durationInHours <= 0) {
            return 0;
        }

        return (double) realUserVisits / durationInHours;
    }

    public double calculateAverageErrorsPerHour() {
        if (minTime == null || maxTime == null || minTime.equals(maxTime)) {
            return 0;
        }

        long durationInHours = Duration.between(minTime, maxTime).toHours();
        if (durationInHours <= 0) {
            return 0;
        }

        return (double) errorResponses / durationInHours;
    }

    public double calculateAverageVisitsPerUser() {
        if (uniqueUserIPs.isEmpty() || realUserVisits == 0) {
            return 0;
        }

        return (double) realUserVisits / uniqueUserIPs.size();
    }


    public void printStatistics() {
        System.out.println("4) Список существующих страниц: " + existingPages.size());

        System.out.println("5) Список несуществующих страниц: " + nonExistingPages.size());

        System.out.println("6) Статистика операционных систем:");
        Map<String, Double> osStatistics = getOsStatistics();
        for (Map.Entry<String, Double> entry : osStatistics.entrySet()) {
            System.out.printf("%s: %.2f%%%n", entry.getKey(), entry.getValue() * 100);
        }

        System.out.println("7) Статистика браузеров:");
        Map<String, Double> browserStatistics = getBrowserStatistics();
        for (Map.Entry<String, Double> entry : browserStatistics.entrySet()) {
            System.out.printf("%s: %.2f%%%n", entry.getKey(), entry.getValue() * 100);
        }

        double averageTrafficPerHour = calculateAverageTrafficPerHour();
        double averageVisitsPerHour = calculateAverageVisitsPerHour();
        double averageErrorsPerHour = calculateAverageErrorsPerHour();
        double averageVisitsPerUser = calculateAverageVisitsPerUser();
        int peakVisitsPerSecond = getPeakVisitsPerSecond();
        int maxVisitsPerUser = getMaxVisitsPerUser();


        System.out.printf("8) Средний объем трафика за час: %.2f байт%n", averageTrafficPerHour);
        System.out.printf("9) Среднее количество посещений за час: %.2f%n", averageVisitsPerHour);
        System.out.printf("10) Среднее количество ошибочных запросов за час: %.2f%n", averageErrorsPerHour);
        System.out.printf("11) Средняя посещаемость одним пользователем: %.2f%n", averageVisitsPerUser);
        System.out.printf("12) Пиковая посещаемость (в секунду): %d%n", peakVisitsPerSecond);
        System.out.printf("13) Максимальная посещаемость одним пользователем: %d%n", maxVisitsPerUser);


        System.out.println("14) Ссылочные домены: " + referringDomains.size());
        for (String domain : referringDomains) {
            System.out.print(String.format("[%s]; ", domain));
        }
    }
}



