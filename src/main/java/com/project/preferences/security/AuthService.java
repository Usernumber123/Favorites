package com.project.preferences.security;

import com.project.preferences.model.TokenRequest;
import com.project.preferences.model.User;
import com.project.preferences.model.UserDto;
import com.project.preferences.repository.UserRepository;
import com.project.preferences.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;
    private final ConversionService conversionService;
    private final EmailService emailService;

    public ResponseEntity<String> generateToken(TokenRequest tokenRequest) {
        User person = userRepository.findOneByLogin(tokenRequest.getLogin()).orElseThrow(RuntimeException::new);
        if (!isEmailVerified(person)) { //проверка только тут, чтобы пользователь не смог залогиниться и получить токен
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email not verified");
        }
        if (!passwordEncoder.matches(tokenRequest.getPassword(), person.getPassword())) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok(tokenService.generateToken(person));
    }

    public ResponseEntity<String> signUp(UserDto userDto, String host) {
        Optional<User> person = userRepository.findOneByLogin(userDto.getLogin());
        if (person.isEmpty()) { // если пользователь не существует
            User user = conversionService.convert(userDto, User.class);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEmailVerified(false);
            try {
                UUID verificationToken = UUID.randomUUID();
                user.setVerificationToken(verificationToken);
                userRepository.save(user);
                emailService.sendVerificationEmail(user, host, verificationToken);
                return ResponseEntity.ok("Signed up successfully");
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to sign up");
            }
        } else {
            if (!person.get().isEmailVerified()) { // если Емэйл не подтвержден, отправить новый
                User regUser = person.get();
                UUID verificationToken = UUID.randomUUID();
                regUser.setVerificationToken(verificationToken);
                userRepository.save(regUser);
                emailService.sendVerificationEmail(regUser, host, verificationToken);
                return ResponseEntity.ok("Signed up successfully");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exist");
            }
        }


    }

    public boolean isEmailVerified(User user) {
        return user.isEmailVerified();
    }

}