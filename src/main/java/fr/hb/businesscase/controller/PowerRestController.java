package fr.hb.businesscase.controller;


import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.businesscase.custom_response.CustomResponse;
import fr.hb.businesscase.dto.PowerDTO;
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
    public CustomResponse<Power> create(@RequestBody PowerDTO powerDTO){
        return powerService.createPower(powerDTO);
    }

}
