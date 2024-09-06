package fr.hb.businesscase.controller;

import fr.hb.businesscase.dto.UserRegistrationDTO;
import fr.hb.businesscase.service.AddressService;
import fr.hb.businesscase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping
    public void create(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        userService.createUser(userRegistrationDTO);
    }

    @GetMapping("/verify")
    public void verifyAccount(@RequestParam String token){
        userService.verifyAccount(token);
    }

}
