package fr.hb.businesscase.controller;

import fr.hb.businesscase.dto.ChargingStationDTO;
import fr.hb.businesscase.mapping.UrlRoute;
import fr.hb.businesscase.service.ChargingStationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChargingStationRestController {

    private final ChargingStationService chargingStationService;

    @PostMapping(UrlRoute.STATION_NEW)
    public void createStation(@RequestBody ChargingStationDTO chargingStationDTO){
        chargingStationService.createStation(chargingStationDTO);
    }

}
