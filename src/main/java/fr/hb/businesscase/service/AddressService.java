package fr.hb.businesscase.service;

import fr.hb.businesscase.dto.AddressRegistrationDTO;
import fr.hb.businesscase.dto.AddressStationDTO;
import fr.hb.businesscase.entity.Address;
import fr.hb.businesscase.repository.AddressRepository;
import fr.hb.businesscase.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public Address createAddressFromUserRegistration(AddressRegistrationDTO addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setZipCode(addressDto.getZipCode());
        address.setStreetNumber(addressDto.getStreetNumber());
        address.setStreetName(addressDto.getStreetName());
        return addressRepository.saveAndFlush(address);
    }

    public Address createAddressFromStation(AddressStationDTO addressStationDTO){
        Address address = new Address();
        address.setStreetNumber(addressStationDTO.getStreetNumber());
        address.setStreetName(addressStationDTO.getStreetName());
        address.setCity(addressStationDTO.getCity());
        address.setZipCode(addressStationDTO.getZipCode());
        address.setLongitude(addressStationDTO.getLongitude());
        address.setLatitude(addressStationDTO.getLatitude());
        address.setOwner(userRepository.findById(addressStationDTO.getUserId()).get());
        return addressRepository.saveAndFlush(address);
    }

    public Address findById(Long id){
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
