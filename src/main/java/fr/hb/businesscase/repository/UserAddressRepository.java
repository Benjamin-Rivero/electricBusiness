package fr.hb.businesscase.repository;

import fr.hb.businesscase.entity.User;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.hb.businesscase.entity.UserAddress;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
	List<UserAddress> findByUser(User user);
}