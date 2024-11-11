package fr.hb.businesscase.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.businesscase.custom_response.JwtResponse;
import fr.hb.businesscase.dto.UserLoginDTO;
import fr.hb.businesscase.dto.UserRegistrationDTO;
import fr.hb.businesscase.entity.User;
import fr.hb.businesscase.json_views.JsonViewUser;
import fr.hb.businesscase.security.JwtAuthenticatorService;
import fr.hb.businesscase.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/security")
public class SecurityController {

    private final UserService userService;
    private final JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping("/register")
    @JsonView(JsonViewUser.UserShowView.class)
    public User register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        return userService.createUser(userRegistrationDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody UserLoginDTO userLoginDto){
        return jwtAuthenticatorService.authenticate(userLoginDto);
    }

}