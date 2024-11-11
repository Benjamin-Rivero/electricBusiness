package fr.hb.businesscase.controller;

import fr.hb.businesscase.dto.ChargingStationDTO;
import fr.hb.businesscase.dto.StationHourlyRateDTO;
import fr.hb.businesscase.entity.Station;
import fr.hb.businesscase.mapping.UrlRoute;
import fr.hb.businesscase.service.StationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StationRestController {

    private final StationService stationService;

    @PostMapping(UrlRoute.STATION_NEW)
    public void createStation(@RequestBody ChargingStationDTO chargingStationDTO){
        stationService.persistStation(chargingStationDTO,null);
    }

    @GetMapping(UrlRoute.STATION)
    public List<Station> getAllStations(){
        return stationService.findAll();
    }

    @PutMapping(UrlRoute.STATION_EDIT)
    public Station editStation(@RequestBody ChargingStationDTO chargingStationDTO, @PathVariable String id){
        return stationService.persistStation(chargingStationDTO,id);
    }

    @PutMapping(UrlRoute.STATION_SET_RATE)
    public Station setStationHourlyRate(@RequestBody StationHourlyRateDTO stationHourlyRateDTO, @PathVariable String id){
        return stationService.setStationHourlyRate(stationHourlyRateDTO,id);
    }

    @DeleteMapping(UrlRoute.STATION_DELETE)
    public void delete(@PathVariable String id){
        stationService.deleteStation(id);
    }

}
