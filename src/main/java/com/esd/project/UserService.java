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
        // Checks If Password Matches-----------------------------------

        String confirmpassword = user.getConfirmPassword();
        if (confirmpassword.equals(user.getPassword())) {
            // Password Hashing-------------------------------------------

            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            userRepository.save(user);
            return "success";
        } else {
            return "password_mismatch";
        }
    }

    public String loginUser(String username, String password) {
        // Logic for user login
        User existingUser = userRepository.findByUsername(username);
        System.out.println("exusting user service" + existingUser);
        System.out.println(username);
        System.out.println(password);
        if (existingUser != null && passwordEncoder.matches(password, existingUser.getPassword())) {
            // Successful login
            return "success";
        } else {
            // Login failed
            return "login_failed";
        }
    }
}
