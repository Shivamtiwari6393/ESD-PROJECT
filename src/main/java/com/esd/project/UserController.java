package com.esd.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // INITIALLY--------------------------------------------

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    // AFTER SUBMITTING THE DATA-----------------------------------

    @PostMapping("/registration")
    public String registerUser(User user) {

        String result = userService.registerUser(user);

        if ("success".equals(result)) {
            return "redirect:/user/registration?success";
        } else if ("username_exists".equals(result)) {
            return "redirect:/user/registration?error=username_exists";
        } else {
            return "redirect:/user/registration?passworderror=password_mismatch";
        }
    }
    // LOGIN---------- LOGIN------- LOGIN ---------LOGIN--------- LOGIN-----------

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }
    // AFTER SUBMISSION OF USERNAME AND PASSWORD------------------------------------

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username);
        System.out.println(password);
        String result = userService.loginUser(username, password);
        System.out.println(result);
        if ("success".equals(result)) {
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/user/login?usererror=login_failed";
        }
    }
}
