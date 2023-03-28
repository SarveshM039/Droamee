package com.Droame.Repositrory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Droame.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
