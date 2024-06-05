package com.example.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	private Customer cust1;
	private Customer cust2;
	
	@BeforeEach
	void setUp() {
		cust1 = new Customer();
		cust1.setAcc_no((long) 1);
		cust1.setAddress("Chennai");
		cust1.setAge(30);
		cust1.setBalance(1000);
		cust1.setEmail("email");
		cust1.setName("Name 1");
		cust1.setPassword("password1");
		cust1.setPhone("phone no");
		cust1.setSSN("1234");
		cust1.setUsername("username1");
		
		cust2 = new Customer();
		cust2.setAcc_no((long) 2);
		cust2.setAddress("Chennai");
		cust2.setAge(30);
		cust2.setBalance(1000);
		cust2.setEmail("email 2");
		cust2.setName("Name 2");
		cust2.setPassword("password2");
		cust2.setPhone("phone no2");
		cust2.setSSN("5678");
		cust2.setUsername("username2");
	}
	
	@Test
	void addTest() {
		customerService.add(cust1);
		verify(customerRepository).save(cust1);
	}
	
	@Test
    public void testReadOne() throws Exception {
        long acc_no = 1;
        when(customerRepository.findById(acc_no)).thenReturn(Optional.of(cust1));
        
        customerService.readone(acc_no);
        
    }
	
	@Test
	void updateTest() throws Exception{
		Customer updated_cust = new Customer();
		updated_cust.setAcc_no((long) 1);
		updated_cust.setAddress("updated Chennai");
		updated_cust.setAge(31);
		updated_cust.setBalance(2000);
		updated_cust.setEmail("updated email");
		updated_cust.setName("updated Name 1");
		updated_cust.setPassword("updated password1");
		updated_cust.setPhone("updated phone no");
		updated_cust.setSSN("updated 1234");
		updated_cust.setUsername("updated username1");
		
		when(customerRepository.findById((long) 1)).thenReturn(Optional.of(cust1));
		customerService.update(1, updated_cust);
		assertEquals((long)1, cust1.getAcc_no());
		assertEquals("updated Chennai", cust1.getAddress());
		assertEquals(31, cust1.getAge());
		assertEquals(2000, cust1.getBalance());
		assertEquals("updated email", cust1.getEmail());
		assertEquals("updated Name 1", cust1.getName());
		assertEquals("updated password1", cust1.getPassword());
		assertEquals("updated phone no", cust1.getPhone());
		assertEquals("updated 1234", cust1.getSSN());
		assertEquals("updated username1", cust1.getUsername());
		
		verify(customerRepository).save(cust1);
	}
	
	@Test
    public void testDelete() throws Exception {
        long acc_no = 1;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setAcc_no(acc_no);
        when(customerRepository.findById(acc_no)).thenReturn(Optional.of(expectedCustomer));
        
        customerService.delete(acc_no);
        
        verify(customerRepository).delete(expectedCustomer);
    }
	
	@Test
	void testDeposit() throws Exception{
		float bal = 500;
		when(customerRepository.findById((long) 1)).thenReturn(Optional.of(cust1));
		customerService.deposit(1, bal);
		assertEquals(1500, cust1.getBalance());
		verify(customerRepository).save(cust1);
	}
	
	@Test
	void testWithdraw() throws Exception{
		float bal = 500;
		when(customerRepository.findById((long) 1)).thenReturn(Optional.of(cust1));
		customerService.withdraw(1, bal);
		assertEquals(500, cust1.getBalance());
		verify(customerRepository).save(cust1);
	}
	
    @Test
    void readTest() {
        
        List<Customer> sampleCustomers = new ArrayList<Customer>();
        
        sampleCustomers.add(cust1);
        sampleCustomers.add(cust2);
       
        when(customerRepository.findAll()).thenReturn(sampleCustomers);

       
        List<Customer> result = customerService.read();

        
        verify(customerRepository).findAll();
        verifyNoMoreInteractions(customerRepository);

        assertEquals(sampleCustomers, result);
    }

	
}
