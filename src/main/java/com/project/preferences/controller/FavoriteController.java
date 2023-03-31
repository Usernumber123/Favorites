package com.project.preferences.controller;

import com.project.preferences.api.FavoritesApi;
import com.project.preferences.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class FavoriteController implements FavoritesApi {

    private final UserService userService;


    @Override
    public ResponseEntity<Void> saveFavorites(com.project.preferences.model.@Valid FavoriteDto favoriteDto) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = authentication.getName();
        userService.saveFavorite(favoriteDto, username);
        return ResponseEntity.ok().build();
    }
}
