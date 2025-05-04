package com.wett.wettserver.mail.controllers;

import com.wett.wettserver.mail.representation_models.ContactForm;
import com.wett.wettserver.mail.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/register-to-contact")
    public ResponseEntity<String> registerToContact(
        @RequestBody ContactForm form
    ) {
        emailService.sendEmail(
            "[Wett] Yêu cầu kết nối",
            "Tên: " + form.getFullName() + "\n" +
                "Email: " + form.getEmail() + "\n" +
                "Lời nhắn: " + form.getMessage());
        return ResponseEntity.ok("Sent");
    }
}
