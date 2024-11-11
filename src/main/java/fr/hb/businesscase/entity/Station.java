package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewStation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Station {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@JsonView(JsonViewStation.Name.class)
	@Column(nullable = false)
	private String name;

	@JsonView(JsonViewStation.Direction.class)
	private String accessDirective;

	@JsonView(JsonViewStation.OnStand.class)
	@Column(nullable = false)
	private Boolean onStand;

	@JsonView(JsonViewStation.CreatedAt.class)
	@Column(nullable = false)
	private LocalDateTime createdAt;

	@JsonView(JsonViewStation.UpdatedAt.class)
	private LocalDateTime updatedAt;

	@JsonView(JsonViewStation.Slug.class)
	private String slug;


	@OneToMany(mappedBy = "station")
	private List<Favorite> favorites = new ArrayList<>();

	@OneToMany(mappedBy = "station")
	private List<Booking> bookings = new ArrayList<>();

	@OneToMany(mappedBy = "station")
	private List<Review> reviews = new ArrayList<>();

	@OneToMany(mappedBy = "station")
	private List<HourlyRate> hourlyRates = new ArrayList<>();

	@OneToMany(mappedBy = "station")
	private List<Media> medias = new ArrayList<>();

	@ManyToOne
	private Power power;

	@ManyToOne
	private Address address;

	@Column(nullable = false)
	private boolean autoAcceptBooking;



}