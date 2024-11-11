package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewBooking;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@JsonView(JsonViewBooking.StartedAt.class)
	private LocalDateTime startedAt;

	@JsonView(JsonViewBooking.FinishedAt.class)
	private LocalDateTime finishedAt;

	@JsonView(JsonViewBooking.Status.class)
	private String status;

	@JsonView(JsonViewBooking.CreatedAt.class)
	private LocalDateTime createdAt;

	@ManyToOne
	private UserAddress userAddress;

	@ManyToOne
	private User user;

	@ManyToOne
	private Station station;



}