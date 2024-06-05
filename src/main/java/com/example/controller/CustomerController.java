package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Customer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService cs;
	
	@GetMapping("/readall")
	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = (ArrayList<Customer>) cs.read();
		return customers;
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Customer cust) {
		cs.add(cust);
	}
	
	
	@GetMapping("/readone/{acc_no}")
	public Customer readOne(@PathVariable long acc_no) throws ResourceNotFoundException {
		return cs.readone(acc_no);
	}
	
	@PutMapping("/update/{acc_no}")
	public Customer update(@PathVariable long acc_no, @RequestBody Customer custNew) 
			throws ResourceNotFoundException {
		return cs.update(acc_no, custNew);
	}
	
	@DeleteMapping("/delete/{acc_no}")
	public void delete(@PathVariable long acc_no) throws ResourceNotFoundException{
		cs.delete(acc_no);
	}
	
	//Custom Queries
	@GetMapping("/loggingin/{username}/{password}")
	public Customer login(@PathVariable String username, @PathVariable String password) {
		Customer c = cs.logindetails(username, password);
		return c;
	}
	
	@PutMapping("/deposit/{acc_no}/{amount}")
	public Customer deposit(@PathVariable Long acc_no, @PathVariable float amount) throws ResourceNotFoundException {
		Customer c = cs.deposit(acc_no,amount);
		return c;
	}
	
	@PutMapping("/withdraw/{acc_no}/{amount}")
	public Customer withdraw(@PathVariable Long acc_no, @PathVariable float amount) throws ResourceNotFoundException {
		Customer c = cs.withdraw(acc_no,amount);
		return c;
	}
	
}
