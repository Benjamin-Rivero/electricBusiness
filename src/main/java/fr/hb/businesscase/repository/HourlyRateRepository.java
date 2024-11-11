package fr.hb.businesscase.repository;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.hb.businesscase.entity.HourlyRate;

@Repository
public interface HourlyRateRepository extends JpaRepository<HourlyRate, Long> {
}