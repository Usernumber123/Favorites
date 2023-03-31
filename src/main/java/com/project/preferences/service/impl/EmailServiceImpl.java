package com.project.preferences.service.impl;

import com.project.preferences.config.ApplicationProperties;
import com.project.preferences.model.User;
import com.project.preferences.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final ApplicationProperties applicationProperties;

    private void sendEmail(String toEmail, String subject, String body) {
        try {
            //отправка Email
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setTo(toEmail);
            helper.setFrom(applicationProperties.getMail().getUsername());
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendVerificationEmail(User user, String host, UUID verificationToken) {
        String verificationLink = UriComponentsBuilder.fromUriString(host)
                .path("/verify-email")
                .queryParam("token", verificationToken)
                .toUriString();
        String subject = "Пожалуйста, подтвердите email";
        String body = "Уважаемый(ая) " + user.getFirstName() + " " + user.getMiddleName() + ",\n\n" +
                "Благодарим вас за регистрацию! Пожалуйста, подтвердите ваш адрес электронной почты, перейдя по следующей ссылке:\n\n" +
                "<a href=\"" + verificationLink + "\">" + verificationLink + "</a>" + ".";
        sendEmail(user.getEmail(), subject, body);
    }

}
