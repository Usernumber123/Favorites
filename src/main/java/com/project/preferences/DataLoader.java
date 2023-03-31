package com.project.preferences;

import com.project.preferences.model.Role;
import com.project.preferences.model.User;
import com.project.preferences.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User adminUser = new User();
        adminUser.setRole(Role.ADMIN);
        adminUser.setEmailVerified(true);
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.setEmail("admin@admin.com");
        adminUser.setMiddleName("admin");
        adminUser.setLastName("admin");
        adminUser.setFirstName("admin");
        adminUser.setBirthday(LocalDate.of(1971, 1, 1));
        adminUser.setLogin("admin");
        Optional<User> person = userRepository.findOneByLogin(adminUser.getLogin());
        if (person.isEmpty()) {
            userRepository.save(adminUser);
        } else {
            log.info("Admin user already exist");
        }
    }
}
