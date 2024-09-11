package fr.hb.businesscase.service;

import fr.hb.businesscase.dto.ChargingStationDTO;
import fr.hb.businesscase.dto.StationHourlyRateDTO;
import fr.hb.businesscase.entity.Address;
import fr.hb.businesscase.entity.ChargingStation;
import fr.hb.businesscase.repository.ChargingStationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;
    private final AddressService addressService;
    private final PowerService powerService;

    public ChargingStation saveStation(ChargingStationDTO chargingStationDTO, Long id){
        ChargingStation station = new ChargingStation();
        station.setId(id);
        station.setName(chargingStationDTO.getName());
        station.setAutoAcceptBooking(false);
        station.setOnStand(chargingStationDTO.isOnStand());
        station.setPower(powerService.findPowerById(chargingStationDTO.getPowerId()));
        Address address = addressService.findById(chargingStationDTO.getAddressStationId());
        station.setAddress(address);
        return chargingStationRepository.saveAndFlush(station);
    }

    public ChargingStation findById(Long id) {
        return chargingStationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<ChargingStation> findAll() {
        return chargingStationRepository.findAll();
    }

    public ChargingStation setStationHourlyRate(StationHourlyRateDTO dto, Long id){
        ChargingStation station = findById(id);
        station.setHourlyRate(dto.getHourlyRate());
        return chargingStationRepository.saveAndFlush(station);
    }

    public ChargingStation persistStation(Object dto, Long id){
        ChargingStation station = null;
        if(dto instanceof ChargingStationDTO){
            station = saveStation((ChargingStationDTO) dto,id);
        }
        if(dto instanceof StationHourlyRateDTO){
            station = setStationHourlyRate((StationHourlyRateDTO) dto,id);
        }
        return station;
    }

}
