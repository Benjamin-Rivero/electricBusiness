package fr.hb.businesscase.controller;

import fr.hb.businesscase.dto.UserRegistrationDTO;
import fr.hb.businesscase.mapping.UrlRoute;
import fr.hb.businesscase.service.AddressService;
import fr.hb.businesscase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping(UrlRoute.USER)
    public void create(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        userService.createUser(userRegistrationDTO);
    }

    @GetMapping(UrlRoute.USER_VERIFY)
    public void verifyAccount(@RequestParam String token){
        userService.verifyAccount(token);
    }

}
