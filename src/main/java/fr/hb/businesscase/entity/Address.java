package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewAddress;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonView(JsonViewAddress.StreetNumber.class)
	private String streetNumber;

	@JsonView(JsonViewAddress.StreetName.class)
	private String streetName;

	@JsonView(JsonViewAddress.Latitude.class)
	private String latitude;

	@JsonView(JsonViewAddress.Longitude.class)
	private String longitude;

	@JsonView(JsonViewAddress.ZipCode.class)
	private String zipCode;

	@JsonView(JsonViewAddress.City.class)
	private String city;

	@OneToMany(mappedBy = "address")
	private List<UserAddress> userAddresses = new ArrayList<>();

	@OneToMany(mappedBy = "address")
	private List<Station> stations = new ArrayList<>();

	@ManyToOne
	private User owner;



}