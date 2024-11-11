package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewMedia;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

    @JsonView(JsonViewMedia.Name.class)
    private String name;

    @JsonView(JsonViewMedia.Extension.class)
    private String extension;

    @ManyToOne
    private Station station;



}