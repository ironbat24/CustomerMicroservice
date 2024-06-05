package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService{

@Autowired
CustomerRepository cr;


	public List<Customer> read() 
	{
		
		return cr.findAll();
		
	}

	
	//Adding 
	public void add(Customer customer)
	{
		cr.save(customer);
			
	}
	
	public Customer readone(long acc_no) throws ResourceNotFoundException{
		Customer cust = cr.findById(acc_no)
				.orElseThrow(
						()->new ResourceNotFoundException("No such customer found for the id: "+acc_no));
		return cust;
		
	}
	
	public Customer update(long acc_no,Customer custNew) throws ResourceNotFoundException{
		Customer custOld = cr.findById(acc_no)
				.orElseThrow(()->new ResourceNotFoundException("Customer not found for the id: "+acc_no));
		custOld.setAge(custNew.getAge());
		custOld.setBalance(custNew.getBalance());
		custOld.setAddress(custNew.getAddress());
		custOld.setEmail(custNew.getEmail());
		custOld.setName(custNew.getName());
		custOld.setPassword(custNew.getPassword());
		custOld.setPhone(custNew.getPhone());
		custOld.setSSN(custNew.getSSN());
		custOld.setUsername(custNew.getUsername());
		cr.save(custOld);
		return custOld;
	}
	
	public void delete(long acc_no) throws ResourceNotFoundException {
		Optional<Customer> custOld = Optional.ofNullable(cr.findById(acc_no)
				.orElseThrow(()->new ResourceNotFoundException("Customer not found for the id: "+acc_no)));
		cr.delete(custOld.get());
	}

	@Override
	public Customer logindetails(String username, String password) {
		return cr.loginQuery(username, password);
	}
	
	
	public Customer deposit(long acc_no,float balance) throws ResourceNotFoundException {
		Customer cust = cr.findById(acc_no)
				.orElseThrow(()->new ResourceNotFoundException("Customer not found for the id: "+acc_no));
		cust.setBalance(cust.getBalance()+balance);
		cr.save(cust);
		return cust;
	}
	
	
	public Customer withdraw(long acc_no,float balance) throws ResourceNotFoundException {
		Customer cust = cr.findById(acc_no)
				.orElseThrow(()->new ResourceNotFoundException("Customer not found for the id: "+acc_no));
		cust.setBalance(cust.getBalance()-balance);
		cr.save(cust);
		return cust;
	}

}
