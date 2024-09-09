package fr.hb.businesscase.controller;

import fr.hb.businesscase.dto.AddressStationDTO;
import fr.hb.businesscase.mapping.UrlRoute;
import fr.hb.businesscase.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AddressRestController {

    private final AddressService addressService;

    @PostMapping(UrlRoute.ADDRESS_NEW_FOR_STATION)
    public void createAddressForStations(@RequestBody AddressStationDTO addressStationDTO) {
        addressService.createAddressFromStation(addressStationDTO);
    }

}
