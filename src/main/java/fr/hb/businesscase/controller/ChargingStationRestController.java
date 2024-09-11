package fr.hb.businesscase.controller;

import fr.hb.businesscase.dto.ChargingStationDTO;
import fr.hb.businesscase.dto.StationHourlyRateDTO;
import fr.hb.businesscase.entity.ChargingStation;
import fr.hb.businesscase.mapping.UrlRoute;
import fr.hb.businesscase.service.ChargingStationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChargingStationRestController {

    private final ChargingStationService chargingStationService;

    @PostMapping(UrlRoute.STATION_NEW)
    public void createStation(@RequestBody ChargingStationDTO chargingStationDTO){
        chargingStationService.persistStation(chargingStationDTO,null);
    }

    @GetMapping(UrlRoute.STATION)
    public List<ChargingStation> getAllStations(){
        return chargingStationService.findAll();
    }

    @PutMapping(UrlRoute.STATION_EDIT)
    public ChargingStation editStation(@RequestBody ChargingStationDTO chargingStationDTO, @PathVariable Long id){
        return chargingStationService.persistStation(chargingStationDTO,id);
    }

    @PutMapping(UrlRoute.STATION_SET_RATE)
    public ChargingStation setStationHourlyRate(@RequestBody StationHourlyRateDTO stationHourlyRateDTO, @PathVariable Long id){
        return chargingStationService.setStationHourlyRate(stationHourlyRateDTO,id);
    }

}
