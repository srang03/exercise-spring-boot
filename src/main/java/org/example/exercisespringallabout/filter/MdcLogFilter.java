package org.example.exercisespringallabout.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class MdcLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId); // 스레드별 요청 ID 설정

            filterChain.doFilter(request, response);
        } finally {
            MDC.clear(); // 요청 종료 시 반드시 정리
        }
    }
}