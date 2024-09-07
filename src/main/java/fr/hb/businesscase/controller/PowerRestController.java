package fr.hb.businesscase.controller;


import fr.hb.businesscase.entity.Power;
import fr.hb.businesscase.mapping.UrlRoute;
import fr.hb.businesscase.service.PowerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PowerRestController {

    private final PowerService powerService;

    @PostMapping(UrlRoute.POWER_NEW)
    public void create(@RequestBody Power power){
        powerService.createPower(power);
    }

}
