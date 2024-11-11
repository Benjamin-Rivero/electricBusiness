package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewReview;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonView(JsonViewReview.CreatedAt.class)
	private LocalDateTime createdAt;

	@JsonView(JsonViewReview.UpdatedAt.class)
	@Column(nullable = true)
	private LocalDateTime updatedAt;

	@JsonView(JsonViewReview.Content.class)
	@Column(columnDefinition = "TEXT")
	private String content;

	@JsonView(JsonViewReview.Rating.class)
	private Float rating;

	@ManyToOne
	private User user;

	@ManyToOne
	private Station station;

}