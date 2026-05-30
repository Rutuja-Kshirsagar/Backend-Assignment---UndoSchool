package com.undoschool.booking.repository;

import com.undoschool.booking.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByOfferingId(Long offeringId);

    // Core Conflict Detection Query using Interval Overlap Mathematics
    @Query(value = "SELECT COUNT(*) FROM sessions s " +
           "JOIN bookings b ON s.offering_id = b.offering_id " +
           "WHERE b.parent_id = :parentId " +
           "AND s.start_time < :endTime AND s.end_time > :startTime", 
           nativeQuery = true)
    long countOverlappingBookedSessions(@Param("parentId") Long parentId, 
                                        @Param("startTime") Instant startTime, 
                                        @Param("endTime") Instant endTime);
}