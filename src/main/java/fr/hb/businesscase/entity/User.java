package fr.hb.businesscase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private LocalDate birthDate;
    private String slug;
    private LocalDate creationDate;
    private String activationToken;

    @OneToMany(mappedBy = "userTo")
    private List<UserReview> userReviewsSent;

    @OneToMany(mappedBy = "userFrom")
    private List<UserReview> userReviewsReceived;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> userAddresses;

    @OneToMany(mappedBy = "owner")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private List<UserFavorite> favorites;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    private boolean isVerified(){
        return activationToken == null;
    }

}
