package fr.hb.businesscase.repository;

import fr.hb.businesscase.entity.User;
import fr.hb.businesscase.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {

    @Query(value = "SELECT ua FROM UserAddress ua WHERE ua.user = ?1 AND ua.isBilling = true")
    Optional<UserAddress> findByUser(User user);

}
