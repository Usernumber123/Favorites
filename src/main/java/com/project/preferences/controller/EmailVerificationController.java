package com.project.preferences.controller;

import com.project.preferences.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

//к этому контроллеру не писалась документация,так как он не должен явно использоваться
@RestController
@RequestMapping("/verify-email")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> verifyEmail(@RequestParam("token") UUID token) {
        return userService.verifyEmail(token);
    }
}
