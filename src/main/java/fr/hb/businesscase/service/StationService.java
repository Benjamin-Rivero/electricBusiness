package fr.hb.businesscase.service;

import fr.hb.businesscase.dto.ChargingStationDTO;
import fr.hb.businesscase.dto.StationHourlyRateDTO;
import fr.hb.businesscase.entity.Address;
import fr.hb.businesscase.entity.HourlyRate;
import fr.hb.businesscase.entity.Station;
import fr.hb.businesscase.repository.StationRepository;
import fr.hb.businesscase.utils.Slugger;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    private final AddressService addressService;
    private final PowerService powerService;
    private final Slugger slugger;

    public Station saveStation(ChargingStationDTO chargingStationDTO, String id){
        Station station = new Station();
        station.setId(id);
        station.setName(chargingStationDTO.getName());
        station.setAutoAcceptBooking(false);
        station.setOnStand(chargingStationDTO.isOnStand());
        station.setAccessDirective(chargingStationDTO.getInstruction());
        station.setPower(powerService.findPowerById(chargingStationDTO.getPowerId()));
        station.setCreatedAt(LocalDateTime.now());
        station.setSlug(slugger.slugify(station.getName()));
        Address address = addressService.findById(chargingStationDTO.getAddressStationId());
        station.setAddress(address);
        return stationRepository.saveAndFlush(station);
    }

    public Station findById(String id) {
        return stationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    public Station setStationHourlyRate(StationHourlyRateDTO dto, String id){
        Station station = findById(id);
        HourlyRate rate = new HourlyRate();
        rate.setValue(dto.getValue());
        station.getHourlyRates().add(rate);
        return stationRepository.saveAndFlush(station);
    }

    public Station persistStation(Object dto, String id){
        Station station = null;
        if(dto instanceof ChargingStationDTO){
            station = saveStation((ChargingStationDTO) dto,id);
        }
        if(dto instanceof StationHourlyRateDTO){
            station = setStationHourlyRate((StationHourlyRateDTO) dto,id);
        }
        return station;
    }

    public void deleteStation(String id) {
        Station station = findById(id);
        station.setName("Borne supprimée");
        station.setSlug(null);
        station.setAddress(null);
        station.setAccessDirective(null);
        station.setFavorites(null);
        stationRepository.saveAndFlush(station);
    }
}
