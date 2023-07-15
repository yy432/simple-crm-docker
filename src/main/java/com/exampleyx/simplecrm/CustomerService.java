package com.exampleyx.simplecrm;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// Service is where we will put our business logic (i.e. decisions, procesing, computations, etc.)
// Service layer will call the repository layer to update the data store.
// CustomerService will need an instance of CustomerRepository
// Remember concept of composition?
// SelfServiceMachine
// Vending Machine IS A SelfServiceMachine (Inheritance)
// Vending Machine HAS A Cashbox (Composition)


import java.util.ArrayList;

public interface CustomerService {
  Customer createCustomer(Customer customer);

  Customer getCustomer(int id);

  List<Customer> getAllCustomers();

  Customer updateCustomer(int id, Customer customer);

  void deleteCustomer(int id);

  Interaction addInteractionToCustomer(int id, Interaction interaction);

}
