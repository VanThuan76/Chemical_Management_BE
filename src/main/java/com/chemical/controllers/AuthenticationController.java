package com.chemical.controllers;

import com.chemical.dto.response.LoginResponseDTO;
import com.chemical.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import com.chemical.common.BaseResponse;
import com.chemical.dto.request.AuthenticationDTO;
import com.chemical.dto.request.UserCreateRequestDTO;
import com.chemical.entity.User;
import com.chemical.config.security.TokenService;
import com.chemical.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth", produces = {"application/json"})
public class AuthenticationController {
    private final UserService userServices;

    public AuthenticationController(UserService userServices) {
        this.userServices = userServices;
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    /**
     * Authenticates user login.
     *
     * @param data Object containing user credentials
     * @return ResponseEntity containing authentication token
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse login(@RequestBody AuthenticationDTO data) {
        var credentials = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(credentials);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        UserResponseDTO loggedInUser = userServices.findByEmailAuth(data.email());

        return BaseResponse.ok(new LoginResponseDTO(token, loggedInUser));
    }

    /**
     * Registers a new user.
     *
     * @param request Object containing user registration data
     * @return ResponseEntity indicating success or failure of registration
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<User> register(@Valid @RequestBody UserCreateRequestDTO request) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        request.setPassword(encryptedPassword);
        User savedUser = userServices.save(request);
        return BaseResponse.created(savedUser);
    }
}
