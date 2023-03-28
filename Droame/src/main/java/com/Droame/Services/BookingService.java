package com.Droame.Services;

import java.util.List;

import com.Droame.entities.Booking;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking updateBooking(Long bookingId, Booking booking);
    void deleteBooking(Long bookingId);
    Booking getBookingById(Long bookingId);
    List<Booking> getAllBookings();
}
