package com.wett.wettserver.mail.representation_models;

import lombok.Data;

@Data
public class ContactForm {
    private String fullName;
    private String email;
    private String message;
}
