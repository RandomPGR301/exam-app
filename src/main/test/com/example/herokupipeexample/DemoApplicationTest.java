package com.example.herokupipeexample;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DemoApplicationTest {

    @Autowired
    private CustomerRepository repository;

//    @Ignore
    @Test
    public void shouldFillOutComponentsWithDataWhenTheApplicationIsStarted() {
        then(this.repository.count()).isEqualTo(6);
    }

//    @Ignore
    @Test
    public void shouldFindTwoMouseCustomers() {
        then(this.repository.findByLastNameStartsWithIgnoreCase("mouse")).hasSize(2);
    }
}