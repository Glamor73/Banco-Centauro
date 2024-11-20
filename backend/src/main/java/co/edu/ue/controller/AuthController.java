package co.edu.ue.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.services.UserService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    // Inyección de dependencias por constructor
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/banco")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        if (userService.registerUser(username, password) != null) {
            return ResponseEntity.ok("User registered successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (userService.checkCredentials(username, password)) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
