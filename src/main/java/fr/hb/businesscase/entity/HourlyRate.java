package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewHourlyRate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class HourlyRate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonView(JsonViewHourlyRate.Value.class)
	private Integer value;

	@JsonView(JsonViewHourlyRate.MinimumDuration.class)
	private Float minimumDuration;

	@ManyToOne
	private Station station;




}