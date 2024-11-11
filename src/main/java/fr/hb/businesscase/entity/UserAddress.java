package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewUserAddress;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class UserAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @JsonView(JsonViewUserAddress.IsBilling.class)
    private boolean isBilling;

    @ManyToOne
    private User user;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "userAddress")
    private List<Booking> booking = new ArrayList<>();



}