package com.zerobase.fastlms.util;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class RequestUtils {
    public String getClientIP(HttpServletRequest request) {
        String clientIP = request.getHeader("X-Forwarded-For");
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }

    public String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }
}
