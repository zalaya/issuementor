package org.backend.issuementor.services.implementations;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.backend.issuementor.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImplementation implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    @Async
    public void send(String email, String username, String token) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Verify your account.");

        Context context = new Context();

        context.setVariable("email", email);
        context.setVariable("username", username);
        context.setVariable("token", token);

        helper.setText(templateEngine.process("verification-email", context), true);

        javaMailSender.send(message);
    }
}
