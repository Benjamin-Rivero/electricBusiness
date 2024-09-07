package fr.hb.businesscase.controller;

import fr.hb.businesscase.dto.BookingDTO;
import fr.hb.businesscase.mapping.UrlRoute;
import fr.hb.businesscase.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BookingRestController {

    private final BookingService bookingService;

    @PostMapping(UrlRoute.BOOKING)
    public void createBooking(@RequestBody BookingDTO bookingDTO){
        bookingService.createBooking(bookingDTO);
    }

    @GetMapping(UrlRoute.BOOKING_ACCEPT)
    public void acceptBooking(@PathVariable Long id){
        bookingService.acceptBooking(id);
    }

    @GetMapping(UrlRoute.BOOKING_REFUSE)
    public void refuseBooking(@PathVariable Long id){
        bookingService.refuseBooking(id);
    }

}
