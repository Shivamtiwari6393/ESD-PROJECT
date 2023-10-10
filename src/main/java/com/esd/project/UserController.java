package com.esd.project;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user) {
        // Check if the username already exists in the database
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null) {
            return "redirect:/user/registration?error=username_exists";
        }
    // PASSWORD CONFIRMATION-------------- PASSWORD CONFIRMATION --------------PASSWORD CONFIRMATION -----------PASSWORD CONFIRMATION -----------
        if (confirmpassword.equals(user.getPassword())) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            userRepository.save(user);
            return "redirect:/user/registration?success";
        } else {
            return "redirect:/user/registration?passworderror=password not matching";
        }

    }
}
