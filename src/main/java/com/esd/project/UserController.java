package com.esd.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user) {
        // Delegate the registration logic to the UserService
        String result = userService.registerUser(user);

        if ("success".equals(result)) {
            return "redirect:/user/registration?success";
        } else if ("username_exists".equals(result)) {
            return "redirect:/user/registration?error=username_exists";
        } else {
            return "redirect:/user/registration?passworderror=password_mismatch";
        }
    }
}