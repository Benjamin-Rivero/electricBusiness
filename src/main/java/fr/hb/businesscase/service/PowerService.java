package fr.hb.businesscase.service;

import fr.hb.businesscase.custom_response.CustomResponse;
import fr.hb.businesscase.dto.PowerDTO;
import fr.hb.businesscase.entity.Power;
import fr.hb.businesscase.repository.PowerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PowerService {

    private final PowerRepository powerRepository;

    public CustomResponse<Power> createPower(PowerDTO powerDTO) {
        Power power = new Power();
        power.setValue(powerDTO.getValue());
        power = powerRepository.saveAndFlush(power);
        return new CustomResponse<>(HttpStatus.CREATED.value(),power,Power.class);
    }

    public Power findPowerById(Long id) {
        return powerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
