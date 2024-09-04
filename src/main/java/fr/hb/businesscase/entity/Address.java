package fr.hb.businesscase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String zipCode;
    private String streetNumber;

    private float latitude;

    private float longitude;

    private boolean isBillingAddress;

    @OneToMany(mappedBy = "address")
    private List<UserAddress> userAddresses;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "address")
    private List<ChargingStation> chargingStations;

}
