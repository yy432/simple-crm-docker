package com.exampleyx.simplecrm;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(int id) {
        super("Customer with id " + id + " not found.");
      }
    
}
