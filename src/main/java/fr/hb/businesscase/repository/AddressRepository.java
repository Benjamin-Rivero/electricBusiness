package fr.hb.businesscase.repository;

import fr.hb.businesscase.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByLongitudeBetweenAndLatitudeBetween(float longitude, float latitude, float longitude2, float latitude2);

}
