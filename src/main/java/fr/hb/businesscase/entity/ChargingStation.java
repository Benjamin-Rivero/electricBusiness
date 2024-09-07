package fr.hb.businesscase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String instruction;

    private boolean onStand;

    private int hourlyRate;

    private LocalTime availabilityStartTime;
    private LocalTime availabilityEndTime;
    private boolean autoAcceptBooking;

    @OneToMany(mappedBy = "chargingStation")
    private List<UserFavorite> favorites;

    @OneToMany(mappedBy = "chargingStation")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "power_id")
    private Power power;

    @OneToMany(mappedBy="station")
    private List<Media> media = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "station_tags",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "charging_station")
    private List<Booking> bookings;

}
