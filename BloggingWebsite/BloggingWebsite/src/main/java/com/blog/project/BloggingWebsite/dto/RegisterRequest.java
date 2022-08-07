package com.blog.project.BloggingWebsite.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String password;
    private String email;

}
