package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	//Login
	@Query(value="select * from customer c where c.username=?1 and c.password=?2",nativeQuery=true)
	public Customer loginQuery(String username,String password);
	
	//Deposit
	@Query(value="update customer set balance=?1 where acc_no=?2",nativeQuery=true)
	public Customer depositQuery(float balance, Long acc_no);
	
	//Withdraw
	

}
