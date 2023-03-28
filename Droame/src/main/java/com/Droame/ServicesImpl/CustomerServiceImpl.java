package com.Droame.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Droame.Exception.NotFoundException;
import com.Droame.Repositrory.CustomerRepository;
import com.Droame.Services.CustomerService;
import com.Droame.entities.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	  @Autowired
	 private CustomerRepository customerRepository;


	  @Override
	    public Customer save(Customer customer) {
	        return customerRepository.save(customer);
	    }

	  
	  @Override
	    public Customer findById(Long id) {
	        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found with id " + id));
	    }

	    @Override
	    public List<Customer> findAll() {
	        return customerRepository.findAll();
	    }

	    @Override
	    public void deleteById(Long id) {
	        customerRepository.deleteById(id);
	    }

	


	    @Override
	    public Customer update(Long id,Customer customer) {
	        Customer existingCustomer = findById(customer.getId());
	        existingCustomer.setName(customer.getName());
	        existingCustomer.setEmail(customer.getEmail());
	        existingCustomer.setPhone(customer.getPhone());
	        existingCustomer.setLocation(customer.getLocation());
	        return customerRepository.save(existingCustomer);
	    }}

   

