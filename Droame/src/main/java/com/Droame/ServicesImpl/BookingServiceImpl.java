package com.Droame.ServicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Droame.Exception.NotFoundException;
import com.Droame.Repositrory.BookingRepository;
import com.Droame.Repositrory.CustomerRepository;
import com.Droame.Services.BookingService;
import com.Droame.entities.Booking;
import com.Droame.entities.Customer;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        Customer customer = customerRepository.findById(booking.getCustomer().getId())
                .orElseThrow(() -> new NotFoundException("Customer not found with id " + booking.getCustomer().getId()));
        booking.setCustomer(customer);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking booking) {
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found with id " + bookingId));
        existingBooking.setDroneShotDetail(booking.getDroneShotDetail());
        existingBooking.setLocation(booking.getLocation());
        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found with id " + bookingId));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}

