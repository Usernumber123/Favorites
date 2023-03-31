package com.project.preferences.service;

import com.project.preferences.model.User;

import java.util.UUID;

public interface EmailService {
    void sendVerificationEmail(User user, String host, UUID verificationToken);
}
