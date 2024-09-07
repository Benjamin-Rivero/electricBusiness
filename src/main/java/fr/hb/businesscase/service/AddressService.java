package fr.hb.businesscase.service;

import fr.hb.businesscase.dto.AddressRegistrationDTO;
import fr.hb.businesscase.dto.AddressStationDTO;
import fr.hb.businesscase.entity.Address;
import fr.hb.businesscase.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address createAddressFromUserRegistration(AddressRegistrationDTO addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setZipCode(addressDto.getZipCode());
        address.setStreetNumber(addressDto.getStreet().split(" ")[0]);
        address.setStreet((addressDto.getStreet().split(" ",2)[1]));
        return addressRepository.saveAndFlush(address);
    }

    public Address createAddressFromStation(AddressStationDTO addressStationDTO){
        Address address = new Address();
        address.setCity(addressStationDTO.getCity());
        address.setStreetNumber(addressStationDTO.getStreet().split(" ")[0]);
        address.setStreet((addressStationDTO.getStreet().split(" ",2)[1]));
        address.setLongitude(addressStationDTO.getLongitude());
        address.setLatitude(addressStationDTO.getLatitude());
        return addressRepository.saveAndFlush(address);
    }



}
