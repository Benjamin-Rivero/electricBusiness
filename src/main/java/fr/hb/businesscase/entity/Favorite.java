package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.businesscase.entity.embedded.FavoriteId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewFavorite;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Favorite {

	@EmbeddedId
	private FavoriteId id;

	@MapsId("userId")
    @ManyToOne
    private User user;

	@MapsId("stationId")
    @ManyToOne
    private Station station;

    @JsonView(JsonViewFavorite.CreatedAt.class)
    private LocalDateTime createdAt;



}