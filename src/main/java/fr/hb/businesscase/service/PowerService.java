package fr.hb.businesscase.service;

import fr.hb.businesscase.entity.Power;
import fr.hb.businesscase.repository.PowerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PowerService {

    private final PowerRepository powerRepository;

    public Power createPower(Power power) {
        return powerRepository.saveAndFlush(power);
    }

    public Power findPowerById(Long id) {
        return powerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
