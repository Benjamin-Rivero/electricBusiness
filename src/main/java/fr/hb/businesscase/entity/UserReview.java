package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewUserReview;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class UserReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonView(JsonViewUserReview.Content.class)
	private String content;

	@JsonView(JsonViewUserReview.Rating.class)
	private float rating;

	@JsonView(JsonViewUserReview.CreatedAt.class)
	private LocalDate createdAt;

	@JsonView(JsonViewUserReview.UpdatedAt.class)
	private LocalDateTime updatedAt;

	@ManyToOne
	private User userFrom;

	@ManyToOne
	private User userTo;



}