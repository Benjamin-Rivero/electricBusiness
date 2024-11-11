package fr.hb.businesscase.entity.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public class FavoriteId {

	private String userId;

	private String stationId;

}
