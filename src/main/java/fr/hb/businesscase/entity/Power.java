package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewPower;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Power {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @JsonView(JsonViewPower.Value.class)
    private Float value;



}