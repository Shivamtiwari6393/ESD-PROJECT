package com.esd.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.esd.project.dto.UserDTO;
import com.esd.project.entities.User;
import com.esd.project.services.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTRATION---REGISTRATION------------REGISTRATION---------REGISTRATION

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
    // AFTER SUBMISSION OF USERNAME , PASSWORD----------------------------------

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        String result = userService.loginUser(username, password);
        if ("success".equals(result)) {
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/user/login?usererror=login_failed";
        }
    }

    // DASHBOARD--------DASHBOARD---------DASHBOARD----------DASHBOARD----------DASHBOARD---------------------------------------

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    // FIND_ALL_USER--------FIND_ALL_USER---------------FIND_ALL_USER--------------FIND_ALL_USER-------------------------------------------

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUserInfo(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return ResponseEntity.ok().body(users);

    }
    // DELETE_USER----------DELETE_USER----------DELETE_USER-------DELETE_USER------------------------------------------------------

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String message;

        if (userService.deleteUser(userId) == 1) {
            message = "User with ID " + userId + " has been deleted successfully.";
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } else if (userService.deleteUser(userId) == 0) {
            message = "User with ID " + userId + " already deleted.";
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } else {
            message = "User with ID " + userId + " not found.";
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        }
    }

    // UPDATE_USER-------------UPDATE_USER----------UPDATE_USER-------------------UPDATE_USER------------------------------------------------

    @PutMapping("/{userId}/updatepassword")
    public ResponseEntity<String> updatePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        User updatedUser = userService.updatePassword(userId, newPassword);

        if (updatedUser != null) {
            return ResponseEntity.ok().body("Password Updated Succesfully");
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("USER NOT FOUND");
        }
    }
    // FIND_USER_BY_ID----------FIND_USER_BY_ID----------FIND_USER_BY_ID----------FIND_USER_BY_ID----------FIND_USER_BY_ID------------------

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return ResponseEntity.ok().body("USER NOT FOUND");
        }
    }

    // FIND USER BY NAME--------------------------------------------------

    @GetMapping("/find/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ******************************************************************************************************************************************
}
