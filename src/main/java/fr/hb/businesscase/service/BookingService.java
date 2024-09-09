package fr.hb.businesscase.service;

import fr.hb.businesscase.dto.BookingDTO;
import fr.hb.businesscase.entity.Booking;
import fr.hb.businesscase.entity.User;
import fr.hb.businesscase.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ChargingStationService chargingStationService;
    private final UserAddressService userAddressService;

    public Booking createBooking(BookingDTO bookingDTO){
        Booking booking = new Booking();
        booking.setUser(userService.findById(bookingDTO.getUserId()));
        booking.setCharging_station(chargingStationService.findById(bookingDTO.getStationId()));
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setStatus(null);
        booking.setUser_address(userAddressService.findByUser(booking.getUser()));
        return bookingRepository.saveAndFlush(booking);
    }

    public Booking findById(Long id){
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Booking acceptBooking(Long id){
        Booking booking = findById(id);
        booking.setStatus(true);
        return bookingRepository.saveAndFlush(booking);
    }

    public Booking refuseBooking(Long id){
        Booking booking = findById(id);
        booking.setStatus(false);
        return bookingRepository.saveAndFlush(booking);
    }

    public List<Booking> findBookingsInProgressByUser(String userId){
        User user = userService.findById(userId);
        return bookingRepository.findByStatusNotAndUser(false,user);
    }

    public List<Booking> findPastBookingsByUser(String userId){
        User user = userService.findById(userId);
        return bookingRepository.findPastBookings(user);
    }

}
