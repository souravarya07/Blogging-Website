package com.blog.project.BloggingWebsite.Service;

import com.blog.project.BloggingWebsite.Repository.UserRepository;
import com.blog.project.BloggingWebsite.dto.RegisterRequest;
import com.blog.project.BloggingWebsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        userRepository.save(user);
    }
}
