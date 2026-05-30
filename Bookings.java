package com.undoschool.booking.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Column(name = "offering_id", nullable = false)
    private Long offeringId;

    @Column(name = "booked_at")
    private Instant bookedAt = Instant.now();

    // No-args Constructor
    public Booking() {
    }

    // All-args Constructor
    public Booking(Long id, Long parentId, Long offeringId, Instant bookedAt) {
        this.id = id;
        this.parentId = parentId;
        this.offeringId = offeringId;
        this.bookedAt = bookedAt;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
    }

    public Long getOfferingId() {
        return offeringId;
    }

    public Instant getBookedAt() {
        return bookedAt;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setOfferingId(Long offeringId) {
        this.offeringId = offeringId;
    }

    public void setBookedAt(Instant bookedAt) {
        this.bookedAt = bookedAt;
    }
}