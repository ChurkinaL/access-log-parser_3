package ru.parser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    private final String ipAddress;
    private final LocalDateTime dateTime;
    private final HttpMethod httpMethod;
    private final String requestPath;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String logLine) {
        // Регулярное выражение для лога
        String logPattern =
                "^(\\S+) - - \\[(\\d+/[A-Za-z]+/\\d+:\\d+:\\d+:\\d+ \\+\\d{4})] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+) \"([^\"]*)\" \"([^\"]*)\"$";
        Pattern pattern = Pattern.compile(logPattern);
        Matcher matcher = pattern.matcher(logLine);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Неверный формат строки журнала: " + logLine);
        }

        // IP Address
        this.ipAddress = matcher.group(1);

        // Date and Time
        String dateTimeStr = matcher.group(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        ZoneId zoneId = ZoneId.ofOffset("GMT", ZoneOffset.ofTotalSeconds(Integer.parseInt(dateTimeStr.split("\\s+")[1])));
        ZonedDateTime zonedDateTime;
        try {
            zonedDateTime = ZonedDateTime.parse(dateTimeStr, formatter.withZone(zoneId));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты в логе: " + dateTimeStr, e);
        }
        this.dateTime = zonedDateTime.toLocalDateTime();

        // HTTP Method
        this.httpMethod = LogEntry.HttpMethod.valueOf(matcher.group(3));

        // Request Path
        this.requestPath = matcher.group(4);

        // Response Code
        this.responseCode = Integer.parseInt(matcher.group(6));

        // Response Size
        this.responseSize = Integer.parseInt(matcher.group(7));

        // Referer
        String refererStr = matcher.group(8);
        this.referer = refererStr.equals("-") ? null : refererStr;

        // User-Agent
        String userAgentStr = matcher.group(9);
        this.userAgent = new UserAgent(userAgentStr);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    public enum HttpMethod {
        GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH, TRACE
    }
}





