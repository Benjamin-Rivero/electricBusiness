package fr.hb.businesscase.service;

import fr.hb.businesscase.dto.BookingDTO;
import fr.hb.businesscase.entity.Booking;
import fr.hb.businesscase.entity.User;
import fr.hb.businesscase.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final StationService stationService;
    private final UserAddressService userAddressService;

    public Booking createBooking(BookingDTO bookingDTO){
        Booking booking = new Booking();
        booking.setUser(userService.findById(bookingDTO.getUserId()));
        booking.setStation(stationService.findById(bookingDTO.getStationId()));
        booking.setStartedAt(bookingDTO.getStartDate());
        booking.setFinishedAt(bookingDTO.getEndDate());
        booking.setStatus(null);
        booking.setUserAddress(userAddressService.findById(bookingDTO.getUserAddressId()));
        return bookingRepository.saveAndFlush(booking);
    }

    public Booking findById(String id){
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Booking acceptBooking(String id){
        Booking booking = findById(id);
        booking.setStatus("ACCEPTED");
        return bookingRepository.saveAndFlush(booking);
    }

    public Booking refuseBooking(String id){
        Booking booking = findById(id);
        booking.setStatus("REFUSED");
        return bookingRepository.saveAndFlush(booking);
    }

    public List<Booking> findBookingsInProgressByUser(String userId){
        User user = userService.findById(userId);
        return bookingRepository.findByStatusAndUser("REFUSED",user);
    }

    public List<Booking> findPastBookingsByUser(String userId){
        User user = userService.findById(userId);
        return bookingRepository.findPastBookings(user);
    }

}
