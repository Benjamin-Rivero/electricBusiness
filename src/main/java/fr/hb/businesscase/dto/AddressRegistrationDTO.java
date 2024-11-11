package fr.hb.businesscase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRegistrationDTO {

    private String streetNumber;

    private String streetName;

    private String city;

    private String zipCode;

    private UserAddressDTO userAddressDto;

}
