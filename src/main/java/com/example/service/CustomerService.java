package com.example.service;

import java.util.List;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Customer;


public interface CustomerService {
	
	public List<Customer> read();
	
	public Customer logindetails(String username, String password);
	
	public void add(Customer customer);
	
	public Customer readone(long acc_no) throws ResourceNotFoundException;
	
	public Customer update(long acc_no,Customer custNew) throws ResourceNotFoundException;
	
	public void delete(long acc_no) throws ResourceNotFoundException;
	
	public Customer deposit(long acc_no,float balance) throws ResourceNotFoundException;
	
	public Customer withdraw(long acc_no,float balance) throws ResourceNotFoundException;
}
