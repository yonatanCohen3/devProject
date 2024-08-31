package com.example.demo;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user){
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return user;
    }
}
