package fr.hb.businesscase.service;

import fr.hb.businesscase.entity.Booking;
import fr.hb.businesscase.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking findById(Long id){
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Booking acceptBooking(Long id){
        Booking booking = findById(id);
        booking.setStatus(true);
        return bookingRepository.save(booking);
    }

    public Booking refuseBooking(Long id){
        Booking booking = findById(id);
        booking.setStatus(false);
        return bookingRepository.save(booking);
    }

}
