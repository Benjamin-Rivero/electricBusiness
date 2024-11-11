package fr.hb.businesscase.repository;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.hb.businesscase.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {
}