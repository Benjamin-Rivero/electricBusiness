package fr.hb.businesscase.repository;

import fr.hb.businesscase.entity.User;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fr.hb.businesscase.entity.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
	List<Booking> findByStatusAndUser(String refused, User user);

	@Query("SELECT b FROM Booking b WHERE b.finishedAt < current_date() ")
	List<Booking> findPastBookings(User user);
}