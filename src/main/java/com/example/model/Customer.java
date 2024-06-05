package com.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long acc_no;
	String name;
	String username;
	String password;
	int age;
	String SSN;
	String address;
	String email;
	String phone;
	float balance;
	
	public Customer(Long acc_no, String name, String username, String password, int age, String sSN, String address,
			String email, String phone, float balance) {
		super();
		this.acc_no = acc_no;
		this.name = name;
		this.username = username;
		this.password = password;
		this.age = age;
		SSN = sSN;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.balance = balance;
	}
	
	

}
