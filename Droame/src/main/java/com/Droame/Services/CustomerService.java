package com.Droame.Services;

import java.util.List;

import com.Droame.entities.Customer;

public interface CustomerService {
    Customer save(Customer customer);
    Customer findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);
    Customer update( Long id ,Customer customer);
	
}