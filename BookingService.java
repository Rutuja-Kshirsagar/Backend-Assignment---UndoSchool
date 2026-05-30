package com.undoschool.booking.service;

import com.undoschool.booking.exception.BookingConflictException;
import com.undoschool.booking.model.Booking;
import com.undoschool.booking.model.Session;
import com.undoschool.booking.repository.BookingRepository;
import com.undoschool.booking.repository.ParentRepository;
import com.undoschool.booking.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final ParentRepository parentRepository;
    private final SessionRepository sessionRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public Booking bookOffering(Long parentId, Long offeringId) {
        // 1. Lock the parent record to handle concurrent execution for the same user account
        parentRepository.findByIdForUpdate(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Parent account not found"));

        // 2. Check if already booked
        if (bookingRepository.existsByParentIdAndOfferingId(parentId, offeringId)) {
            throw new BookingConflictException("Offering is already booked by this parent.");
        }

        // 3. Fetch all sessions for the requested offering
        List<Session> targetSessions = sessionRepository.findByOfferingId(offeringId);
        if (targetSessions.isEmpty()) {
            throw new IllegalArgumentException("Cannot book an offering with no scheduled sessions.");
        }

        // 4. Validate schedule conflicts against existing bookings
        for (Session targetSession : targetSessions) {
            long overlaps = sessionRepository.countOverlappingBookedSessions(
                    parentId, 
                    targetSession.getStartTime(), 
                    targetSession.getEndTime()
            );
            if (overlaps > 0) {
                throw new BookingConflictException("Booking failed: Schedule conflicts with an existing booking.");
            }
        }

        // 5. Commit booking execution
        Booking booking = new Booking();
        booking.setParentId(parentId);
        booking.setOfferingId(offeringId);
        return bookingRepository.save(booking);
    }
}