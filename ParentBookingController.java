package com.undoschool.booking.controller;

import com.undoschool.booking.model.Booking;
import com.undoschool.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class ParentBookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long parentId, 
                                                 @RequestParam Long offeringId) {
        Booking dynamicBooking = bookingService.bookOffering(parentId, offeringId);
        return ResponseEntity.ok(dynamicBooking);
    }
}