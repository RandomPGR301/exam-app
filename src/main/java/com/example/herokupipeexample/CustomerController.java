package com.example.herokupipeexample;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerRepository customerRepository;
//    private Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final MetricRegistry registry = new MetricRegistry();
    private int counter;

    public CustomerController(CustomerRepository customerRepository) {
      this.customerRepository = customerRepository;
    }

    @RequestMapping("/")
    public String welcome() {

        Logger logger = LoggerFactory.getLogger(CustomerController.class);

        registry.meter("welcome").mark();

        logger.info("Testing LOGGER for LEVEL: info nr." + counter);
        counter++;

        return "Welcome to this small REST service. It will accept a GET on /list with a request parameter lastName, and a POST to / with a JSON payload with firstName and lastName as values.";
    }

    @RequestMapping("/list")
    public List<Customer> find(@RequestParam(value="lastName") String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @PostMapping("/")
    	Customer newCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
    		return customerRepository.save(customer);
    	}

}
