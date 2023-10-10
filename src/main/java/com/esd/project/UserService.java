package com.esd.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(User user) {
        // Check if the username already exists in the database
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null) {
            return "username_exists";
        }

        String confirmpassword = user.getConfirmPassword();
        if (confirmpassword.equals(user.getPassword())) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            userRepository.save(user);
            return "success";
        } else {
            return "password_mismatch";
        }
    }
}