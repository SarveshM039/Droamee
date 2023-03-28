package com.Droame.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Droame.Exception.NotFoundException;
import com.Droame.Services.BookingService;
import com.Droame.Services.CustomerService;
import com.Droame.Services.DroneShotDetailService;
import com.Droame.Services.LocationService;
import com.Droame.entities.Booking;
import com.Droame.entities.Customer;
import com.Droame.entities.DroneShotDetail;
import com.Droame.entities.Location;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private DroneShotDetailService droneShotDetailService;

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/bookings/{bookingId}")
    public Booking getBookingById( @PathVariable Long bookingId) throws NotFoundException {
        return bookingService.getBookingById(bookingId);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) throws NotFoundException {
        Customer customer = customerService.findById(booking.getCustomer().getId());
        Location location = locationService.getLocationById(booking.getLocation().getId());
        DroneShotDetail droneShotDetail = droneShotDetailService.getDroneShotDetailById(booking.getDroneShotDetail().getId());

        booking.setCustomer(customer);
        booking.setLocation(location);
        booking.setDroneShotDetail(droneShotDetail);
        Booking createdBooking = bookingService.createBooking(booking);

        URI uri = UriComponentsBuilder.fromPath("/api/v1/bookings/{id}").buildAndExpand(createdBooking.getId()).toUri();
        return ResponseEntity.created(uri).body(createdBooking);
    }

    @PutMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestBody Booking booking) throws NotFoundException {
        Booking updatedBooking = bookingService.updateBooking(bookingId, booking);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) throws NotFoundException {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) throws NotFoundException {
        return customerService.findById(customerId);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer createdCustomer = customerService.save(customer);
        URI uri = UriComponentsBuilder.fromPath("/api/v1/customers/{id}").buildAndExpand(createdCustomer.getId()).toUri();
        return ResponseEntity.created(uri).body(createdCustomer);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) throws NotFoundException {
        Customer updatedCustomer = customerService.update(customerId, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) throws NotFoundException {
        customerService.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}
