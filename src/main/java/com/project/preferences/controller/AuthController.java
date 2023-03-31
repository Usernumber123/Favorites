package com.project.preferences.controller;

import com.project.preferences.api.AuthApi;
import com.project.preferences.model.TokenRequest;
import com.project.preferences.model.UserDto;
import com.project.preferences.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Override
    public ResponseEntity<String> signUp(@Valid UserDto userDto) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        return authService.signUp(userDto, host);
    }

    // login контроллер
    @Override
    public ResponseEntity<String> token(@Valid TokenRequest tokenRequest) {
        return authService.generateToken(tokenRequest);
    }
}