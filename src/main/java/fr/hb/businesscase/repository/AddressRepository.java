package fr.hb.businesscase.repository;

import fr.hb.businesscase.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByLongitudeBetweenAndLatitudeBetween(double longitude, double latitude, double longitude2, double latitude2);

}
