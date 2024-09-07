package fr.hb.businesscase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargingStationDTO {

    private String name;

    private Long powerId;

    private String instruction;

    private boolean onStand;

    private AddressStationDTO addressStation;

}
