package br.com.reservix.core.domain.entities.reservation;


import br.com.reservix.core.domain.entities.ReservationStatus;
import br.com.reservix.core.domain.entities.User;
import br.com.reservix.core.domain.entities.invitation.InvalidReservationPeriodException;
import br.com.reservix.core.domain.entities.room.Room;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private Room room;
    private User user;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private ReservationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime cancelledAt;


    public Reservation() {
    }

    public Reservation(Long id, Room room, User user, LocalDateTime startDateTime, LocalDateTime endDateTime, ReservationStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime cancelledAt) {
        this.id = id;
        this.room = room;
        this.user = user;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cancelledAt = cancelledAt;
    }

    public static void ensureValidPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            throw new InvalidReservationPeriodException("Invalid reservation period");
        }

        if (!endDateTime.isAfter(startDateTime)) {
            throw new InvalidReservationPeriodException("Invalid reservation period");
        }

        if (!startDateTime.isAfter(LocalDateTime.now())) {
            throw new ReservationInPastException("Invalid reservation period");
        }
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getStartDateTime() { return startDateTime; }
    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }

    public LocalDateTime getEndDateTime() { return endDateTime; }
    public void setEndDateTime(LocalDateTime endDateTime) { this.endDateTime = endDateTime; }

    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }



    public static Reservation create(
            Room room,
            User user,
            LocalDateTime start,
            LocalDateTime end
    ) {

        return new Reservation(
                null,
                room,
                user,
                start,
                end,
                ReservationStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public static void validateMaximumDuration(
            LocalDateTime startAt,
            LocalDateTime endAt) {

        Duration duration = Duration.between(startAt, endAt);

        if (duration.compareTo(Duration.ofHours(4)) > 0) {
            throw new ReservationMaximumDurationExceededException("Invalid reservation maximum duration");
        }
    }


    public static void validateMaximumAdvance(
            LocalDateTime startDateTime) {
        if (startDateTime.isAfter(LocalDateTime.now().plusDays(90))) {
            throw new ReservationMaximumAdvanceExceededException("Reservation maximum advance duration");
        }
    }



    public void cancel() {

        if (this.status == ReservationStatus.CANCELLED) {
            throw new ReservationAlreadyCancelledException("Reservation already cancelled");
        }

        this.status = ReservationStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
    public void ensureBelongsTo(Long userId) {

        if (!this.user.getId().equals(userId)) {
            throw new ReservationAccessDeniedException("Reservation access denied");
        }
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room.getName() +
                ", user=" + user +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void finish() {

        if (this.status != ReservationStatus.ACTIVE) {
            throw new IllegalStateException("Reservation is not active");
        }

        this.status = ReservationStatus.FINISHED;
        this.updatedAt = LocalDateTime.now();
    }
}
