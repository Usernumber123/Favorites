package com.project.preferences.controller;

import com.project.preferences.api.UsersReportApi;
import com.project.preferences.model.UserDto;
import com.project.preferences.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController implements UsersReportApi {
    private final UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> usersReport() {
        return ResponseEntity.ok(userService.findAll());
    }
}