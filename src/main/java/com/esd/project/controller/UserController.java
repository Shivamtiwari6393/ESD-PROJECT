package com.esd.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTRATION---REGISTRATION------------REGISTRATION---------REGISTRATION
    @CrossOrigin(origins = "*")
    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    // AFTER SUBMITTING THE DATA-----------------------------------
    @CrossOrigin(origins = "*")
    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(User user) {
        String result = userService.registerUser(user);

        if ("success".equals(result)) {

            return ResponseEntity.ok("Registration successful");
        }

        return ResponseEntity.status(409).body("Username already exists");

    }

    // LOGIN---------- LOGIN------- LOGIN ---------LOGIN--------- LOGIN-----------
    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // AFTER SUBMISSION OF USERNAME , PASSWORD----------------------------------
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        String result = userService.loginUser(username, password);
        if ("success".equals(result)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Login failed invalid username or password");
        }
    }

    // DASHBOARD--------DASHBOARD---------DASHBOARD----------DASHBOARD----------DASHBOARD---------------------------------------
    @CrossOrigin(origins = "*")
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    // FIND_ALL_USER--------FIND_ALL_USER---------------FIND_ALL_USER--------------FIND_ALL_USER-------------------------------------------
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUserInfo(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return ResponseEntity.ok().body(users);

    }

    // DELETE_USER----------DELETE_USER----------DELETE_USER-------DELETE_USER------------------------------------------------------
    @CrossOrigin(origins = "*")
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
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        } else {
            message = "User with ID " + userId + " not found.";
            return ((BodyBuilder) ResponseEntity.notFound()).body(message);
        }

    }

    // UPDATE_USER_PASSWORD-------------UPDATE_USER_PASSWORD----------UPDATE_USER_PASSWORD-------------------UPDATE_USER_PASSWORD----
    @CrossOrigin(origins = "*")
    @PutMapping("/{userId}/updatepassword")
    public ResponseEntity<String> updatePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        User updatedUser = userService.updatePassword(userId, newPassword);

        if (updatedUser != null) {
            return ResponseEntity.ok().body("Password Updated Succesfully");
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("USER NOT FOUND");
        }
    }

    // UPDATE_STATUS----------------UPDATE_STATUS-----------------UPDATE_STATUS----------------UPDATE_STATUS-----------------
    @CrossOrigin(origins = "*")
    @PutMapping("/{userId}/updatestatus/{newStatus}")
    public ResponseEntity<?> updateUserStatus(
            @PathVariable Long userId,
            @PathVariable int newStatus) {
        UserDTO updatedUser = userService.updateUserStatus(userId, newStatus);

        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            String message = "User with ID " + userId + " not found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // FIND_USER_BY_ID----------FIND_USER_BY_ID----------FIND_USER_BY_ID----------FIND_USER_BY_ID----------FIND_USER_BY_ID------------------
    @CrossOrigin(origins = "*")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("USER NOT FOUND");
        }
    }

    // FIND USER BY NAME--------------------------------------------------
    @CrossOrigin(origins = "*")
    @GetMapping("/find/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("USER NOT FOUND");
        }
    }

    // FIND_USER_BY_STATUS----------------------------------------------------------------
    @CrossOrigin(origins = "*")
    @GetMapping("/find/status/{status}")
    public ResponseEntity<?> getUsersByStatus(@PathVariable int status) {
        List<UserDTO> userDTOs = userService.getUsersByStatus(status);

        if (userDTOs.isEmpty()) {
            // If no users are found, return a custom message
            String message = "No users with status " + status + " found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            // If users are found, return the list of users
            return new ResponseEntity<>(userDTOs, HttpStatus.OK);
        }
    }
    // ******************************************************************************************************************************************
}
