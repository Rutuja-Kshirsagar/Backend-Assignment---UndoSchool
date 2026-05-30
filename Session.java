package com.undoschool.booking.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "offering_id", nullable = false)
    private Long offeringId;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    // No-args Constructor
    public Session() {
    }

    // All-args Constructor
    public Session(Long id, Long offeringId, Long teacherId, Instant startTime, Instant endTime) {
        this.id = id;
        this.offeringId = offeringId;
        this.teacherId = teacherId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public Long getOfferingId() {
        return offeringId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setOfferingId(Long offeringId) {
        this.offeringId = offeringId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
}