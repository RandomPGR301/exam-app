package com.example.herokupipeexample;

import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.server.VaadinRequest;
import org.junit.Before;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainViewTests.Config.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MainViewTests {

    @Autowired
    CustomerRepository repository;

    CustomerEditor editor;

    MainView mainView;

    @Before
    public void setup() {
        this.editor = new CustomerEditor(this.repository);
        this.mainView = new MainView(this.repository, editor);
    }

    @Test
    public void shouldInitializeTheGridWithCustomerRepositoryData() {
        int customerCount = (int) this.repository.count();

        then(mainView.grid.getColumns()).hasSize(3);
        then(getCustomersInGrid()).hasSize(customerCount);
    }

    private List<Customer> getCustomersInGrid() {
        ListDataProvider<Customer> ldp = (ListDataProvider) mainView.grid.getDataProvider();
        return new ArrayList<>(ldp.getItems());
    }

    @Configuration
    @EnableAutoConfiguration(exclude = com.vaadin.flow.spring.SpringBootAutoConfiguration.class)
    static class Config {

        @Autowired
        CustomerRepository repository;

        @PostConstruct
        public void initializeData() {
            repository.save(new Customer("Clara", "Cow"));
            repository.save(new Customer("Long", "Bone"));
            repository.save(new Customer("Mickey", "Mouse"));
            repository.save(new Customer("Peter", "Clever"));
            repository.save(new Customer("Minnie", "Mouse"));
        }
    }
}