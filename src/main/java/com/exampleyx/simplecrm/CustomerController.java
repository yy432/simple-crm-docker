package com.exampleyx.simplecrm;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customers")
public class CustomerController {

  // private CustomerService customerService = new CustomerService();

  @Autowired
  private CustomerService customerService;

  // CRUD
  // 1. CREATE
  @PostMapping("")
  public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
  //public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result)  
    // if(result.hasErrors()){
    //   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }
    Customer newCustomer = customerService.createCustomer(customer);
    return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);

  }

  // 2. READ (Get and Get All)
  // Get All Customers
  @GetMapping("") 
  public ResponseEntity<List<Customer>> getAllCustomers() {

    List<Customer> allCustomers = customerService.getAllCustomers();
    return new ResponseEntity<>(allCustomers, HttpStatus.OK);
  }

  // Get Customer
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
    Customer foundCustomer = customerService.getCustomer(id);
    return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    // try{
    // Customer foundCustomer = customerService.getCustomer(id);
    // return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    // }catch(CustomerNotFoundException ex){
    //   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
  }

  // 3. UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
    Customer updatedCustomer = customerService.updateCustomer(id, customer);
    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

  }

  // 4. DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable int id) {
    customerService.deleteCustomer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // Nested Route - Add Interaction to Customer
  // localhost:8080/customers/1/interactions
  @PostMapping("/{id}/interactions")
  public ResponseEntity<Interaction> addInteractionToCustomer(@PathVariable int id, @RequestBody Interaction interaction) {
    Interaction newInteraction = customerService.addInteractionToCustomer(id, interaction);
    return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
  }

}