package com.Droame.Repositrory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Droame.entities.Location;


public interface LocationRepository extends JpaRepository<Location, Long> {

}
