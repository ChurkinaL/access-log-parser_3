package ru.parser;

public class UserAgent {
    private final String operatingSystem;
    private final String browser;

    public UserAgent(String userAgentStr) {
        if (userAgentStr.toLowerCase().contains("windows")) {
            this.operatingSystem = "Windows";
        } else if (userAgentStr.toLowerCase().contains("mac")) {
            this.operatingSystem = "macOS";
        } else if (userAgentStr.toLowerCase().contains("linux")) {
            this.operatingSystem = "Linux";
        } else if (userAgentStr.toLowerCase().contains("android")) {
            this.operatingSystem = "Android";
        } else if (userAgentStr.toLowerCase().contains("iphone") || userAgentStr.toLowerCase().contains("ipad")) {
            this.operatingSystem = "iOS";
        } else {
            this.operatingSystem = "Other";
        }

        if (userAgentStr.toLowerCase().contains("edge")) {
            this.browser = "Edge";
        } else if (userAgentStr.toLowerCase().contains("firefox")) {
            this.browser = "Firefox";
        } else if (userAgentStr.toLowerCase().contains("chrome")) {
            this.browser = "Chrome";
        } else if (userAgentStr.toLowerCase().contains("opera")) {
            this.browser = "Opera";
        } else if (userAgentStr.toLowerCase().contains("safari") && !userAgentStr.toLowerCase().contains("chrome")) {
            this.browser = "Safari";
        } else if (userAgentStr.toLowerCase().contains("msie") || userAgentStr.toLowerCase().contains("trident")) {
            this.browser = "Internet Explorer";
        } else if (userAgentStr.contains("Googlebot")) {
            this.browser = "Googlebot";
        } else if (userAgentStr.contains("YandexBot")) {
            this.browser = "YandexBot";
        } else {
            this.browser = "Other";
        }
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }
}




