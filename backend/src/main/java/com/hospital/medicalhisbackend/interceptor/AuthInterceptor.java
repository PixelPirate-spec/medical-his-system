package com.hospital.medicalhisbackend.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hospital.medicalhisbackend.common.JwtUtils;
import com.hospital.medicalhisbackend.common.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 允许跨域预检请求（OPTIONS）直接通过
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            DecodedJWT decodedJWT = JwtUtils.verifyToken(token);

            if (decodedJWT != null) {
                Long userId = decodedJWT.getClaim("userId").asLong();
                String role = decodedJWT.getClaim("roleName").asString();

                if (userId != null && role != null) {
                    UserContext.set(userId, role);
                    return true;
                }
            }
        }

        // 如果没有携带 Token 或者 Token 解析失败/无效，直接拦截并返回 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("{\"code\":401,\"message\":\"未授权或 Token 已过期，请重新登录\",\"data\":null}");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束后必须清理 ThreadLocal，防止内存泄漏和串行污染
        UserContext.remove();
    }
}