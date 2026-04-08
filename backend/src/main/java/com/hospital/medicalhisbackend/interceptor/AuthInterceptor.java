package com.hospital.medicalhisbackend.interceptor;

import com.hospital.medicalhisbackend.common.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userIdStr = request.getHeader("X-User-Id");
        String role = request.getHeader("X-Role");

        if (userIdStr != null && role != null) {
            try {
                Long userId = Long.parseLong(userIdStr);
                UserContext.set(userId, role);
            } catch (NumberFormatException e) {
                // Invalid user id format, ignore or handle as unauthorized
            }
        }

        // 为了敏捷开发，暂不强制拦截所有，仅做上下文解析
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束后必须清理 ThreadLocal，防止内存泄漏和串行污染
        UserContext.remove();
    }
}