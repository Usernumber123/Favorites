package com.project.preferences;

import com.project.preferences.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class PreferencesApplication {

    public static void main(String[] args) {

        SpringApplication.run(PreferencesApplication.class, args);
    }

}
