package com.project.preferences.interceptor;

import com.project.preferences.model.Logs.Log;
import com.project.preferences.repository.logs.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {
    private final LogRepository logRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String message = String.format("Request - %s %s", request.getMethod(), request.getRequestURI());
        logRequest(message, "INFO");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String message = String.format("Response - %s %s %d", request.getMethod(), request.getRequestURI(), response.getStatus());
        logRequest(message, "INFO");
    }

    //запись лога в базу данных
    private void logRequest(String message, String logLevel) {
        Log log = new Log();
        log.setMessage(message);
        log.setLogLevel(logLevel);
        logRepository.save(log);
        log.log();
    }

}