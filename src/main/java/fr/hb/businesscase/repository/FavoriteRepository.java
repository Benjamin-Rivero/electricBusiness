package fr.hb.businesscase.repository;

import fr.hb.businesscase.entity.embedded.FavoriteId;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.hb.businesscase.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}