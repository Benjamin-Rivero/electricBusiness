package fr.hb.businesscase.repository;

import fr.hb.businesscase.entity.Booking;
import fr.hb.businesscase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByStatusNotAndUser(Boolean status, User user);

    @Query("SELECT b FROM Booking b WHERE b.endDate < CURRENT_DATE AND b.user = ?1")
    List<Booking> findPastBookings(User user);

}
