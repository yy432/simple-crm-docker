package com.exampleyx.simplecrm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.exampleyx.simplecrm.CustomerRepository;
import com.exampleyx.simplecrm.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceImplTest {

@Mock
private CustomerRepository customerRepository;

@InjectMocks
CustomerServiceImpl customerService;

@Test
public void createCustomertest(){
    //mock the data
    Customer newCustomer = new Customer(1, "tony", "Stark", "tony@gmail", "12345678", "CEO", 1975);


//mock the repo method
when(customerRepository.save(newCustomer)).thenReturn(newCustomer);

//Act
Customer savedCustomer = customerService.createCustomer(newCustomer);


//Assert
//assertEquals(newCustomer, savedCustomer);
verify(customerRepository, times(1))
.save(newCustomer);
assertEquals(newCustomer, savedCustomer);


}



@Test
public void getAllCustomers(){

    when(customerRepository.findAll()).thenReturn(Arrays.asList(
        new Customer(1, "Tony", "Stark", "tony@avengers.com", "12345678", "CEO", 1975),
        new Customer(2, "Bruce", "Banner", "bruce@avengers.com", "12345678", "CEO", 1975)
      ));
  
      // Act
      List<Customer> allCustomers = customerService.getAllCustomers();
  
      // Assert
      assertEquals(2, allCustomers.size());
}



@Test
public void getCustomerTest() {
  // Arrange - Mock the data
  Customer newCustomer = new Customer(1, "Tony", "Stark", "tony@avengers.com", "12345678", "CEO", 1975);

  when(customerRepository.findById(1)).thenReturn(Optional.of(newCustomer));

  //Act
  Customer foundCustomer =customerService.getCustomer(1);


 //Assert
 assertEquals(newCustomer, foundCustomer);

}
    
}
