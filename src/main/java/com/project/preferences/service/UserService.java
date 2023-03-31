package com.project.preferences.service;

import com.project.preferences.model.FavoriteDto;
import com.project.preferences.model.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> findAll();

    ResponseEntity<String> verifyEmail(UUID token);

    void saveFavorite(FavoriteDto favoriteDto, String userLogin);
}
