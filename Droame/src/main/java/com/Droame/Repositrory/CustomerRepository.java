package com.Droame.Repositrory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Droame.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

