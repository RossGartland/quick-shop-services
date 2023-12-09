package com.cn.cnauthservice.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;


import com.cn.cnauthservice.payload.request.PasswordResetRequest;
import com.cn.cnauthservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.cn.cnauthservice.models.ERole;
import com.cn.cnauthservice.models.Role;
import com.cn.cnauthservice.models.User;
import com.cn.cnauthservice.payload.request.LoginRequest;
import com.cn.cnauthservice.payload.request.SignupRequest;
import com.cn.cnauthservice.payload.response.JwtResponse;
import com.cn.cnauthservice.payload.response.MessageResponse;
import com.cn.cnauthservice.repositories.RoleRepository;
import com.cn.cnauthservice.repositories.UserRepository;
import com.cn.cnauthservice.security.jwt.JwtUtils;
import com.cn.cnauthservice.security.services.UserDetailsImpl;

/**
 * Auth controller class.
 * Contains auth routes.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    /**
     * Route for logging in.
     * @param loginRequest
     * @return
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    /**
     * Route for signing up.
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    /**
     * Route for getting details about a particular user.
     * @param userID
     * @return
     */
    @GetMapping("/users/{userID}")
    public List<User> getUserDetails(@PathVariable int userID) {
        return authService.getUserDetails(userID);
    }

    /**
     * Resets the password of a given account.
     * @param passwordResetRequest
     * @return
     */
    @PostMapping("/forgot_password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        return authService.resetPassword(passwordResetRequest);
    }

}