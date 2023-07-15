package com.exampleyx.simplecrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleCrmApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ObjectMapper objectmapper; /// used to convert Java Object to Json and vice versa

	@BeforeEach
	void setUp(){
		customerRepository.save(
        new Customer(1, "Tony", "Stark", "tony@avengers.com", "12345678", "CEO", 1975)
		);
		customerRepository.save(
        new Customer(2, "Bruce", "Banner", "bruce@avengers.com", "12345678", "CEO", 1940)
     	 );
	}

	@Test
	void contextLoads() {
	}

	@Test void getCustomerByIdTest() throws Exception {
		//step1: Builda get request to /customers/1
		RequestBuilder request = MockMvcRequestBuilders.get("/customers/1");

		//step2:perform the request and get results
		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").value(1));
		
	}

	@Test
	public void getAllCustomersTest() throws Exception{
		//step 1: Build the request to get all customers
		RequestBuilder request = MockMvcRequestBuilders.get("/customers");

	//step2: Perform the request and get the result
	mockMvc.perform(request)
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.size()").value(2));
	}


	@Test
	public void validCustomerCreationTest() throws Exception {
		//step1: Build the request to create a customer
		Customer newCustomer = new Customer(3, "Ton", "ark", "ony@avengers.com", "12345678", "iron", 1955);
		

		//step 2: Convert the Java object to JSON
		String newCustomerAsJson = objectmapper.writeValueAsString(newCustomer);

		//STEP3: Build the request to create a customer
		RequestBuilder request = MockMvcRequestBuilders.post("/customers")
			.contentType(MediaType.APPLICATION_JSON)
			.content(newCustomerAsJson);

		//step4: Perform the request and get the result
		mockMvc.perform(request)
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id").value(3))
			.andExpect(jsonPath("$.firstName").value("Ton"))
			.andExpect(jsonPath("$.lastName").value("ark"));
	
	}

	@Test
	public void invalidCustomerCreationTest() throws Exception{
		//step1: create an invalid customer
		Customer invalidCustomer =  new Customer(4, "    ", "doc", "strange@avengers.com", "12345678", "avenger", 1985);

		String invalidCustomerAsJson = objectmapper.writeValueAsString(invalidCustomer);

		//step3: build request
		RequestBuilder request = MockMvcRequestBuilders.post("/customers")
			.contentType(MediaType.APPLICATION_JSON)
			.content(invalidCustomerAsJson);     

		
			mockMvc.perform(request)
  				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));



	}

	



} 
