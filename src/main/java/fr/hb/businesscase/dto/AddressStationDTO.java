package fr.hb.businesscase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressStationDTO {

    private String street;

    private String city;

    private float longitude;

    private float latitude;

    private String userId;
}
