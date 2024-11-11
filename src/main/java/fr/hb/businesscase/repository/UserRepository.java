package fr.hb.businesscase.repository;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.hb.businesscase.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	Optional<User> findBySlug(String slug);

	Optional<User> findByEmail(String email);

	Optional<User> findByActivationToken(String token);
}