package fr.hb.businesscase.service;

import fr.hb.businesscase.dto.ChargingStationDTO;
import fr.hb.businesscase.entity.Address;
import fr.hb.businesscase.entity.ChargingStation;
import fr.hb.businesscase.repository.ChargingStationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;
    private final AddressService addressService;
    private final PowerService powerService;

    public ChargingStation createStation(ChargingStationDTO chargingStationDTO){
        ChargingStation station = new ChargingStation();
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

}
