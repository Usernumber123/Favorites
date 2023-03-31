package com.project.preferences.service.impl;

import com.project.preferences.model.Favorite;
import com.project.preferences.model.FavoriteDto;
import com.project.preferences.model.User;
import com.project.preferences.model.UserDto;
import com.project.preferences.repository.UserRepository;
import com.project.preferences.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    @Override

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAllWithFavorite();
        List<UserDto> userDtoList = new ArrayList<>(users.size());
        for (User user : users) {
            UserDto userDto = conversionService.convert(user, UserDto.class);
            if (userDto != null) {
                Favorite favorite = user.getFavorite();
                userDto.setFavorite(conversionService.convert(favorite, FavoriteDto.class));
            }
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public ResponseEntity<String> verifyEmail(UUID token) {
        try {
            User user = userRepository.findByVerificationToken(token).orElseThrow();
            user.setEmailVerified(true);
            user.setVerificationToken(null);
            userRepository.save(user);
            return ResponseEntity.ok("Email successfully verified");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to verify email");
        }
    }

    @Override
    public void saveFavorite(FavoriteDto favoriteDto, String userLogin) {
        Optional<User> userOptional = userRepository.findOneByLogin(userLogin);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Favorite favorite = conversionService.convert(favoriteDto, Favorite.class);
            user.setFavorite(favorite);
            userRepository.save(user);
        }


    }
}
