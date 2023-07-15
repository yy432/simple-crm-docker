package com.exampleyx.simplecrm;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  // 1st one is the Entity that you want to save
  // 2nd one is the data type of id
  // we do not implement the methods here
  // Spring JPA will automatically provide an implementation for us
  // It will become a bean in the Spring Container

}
