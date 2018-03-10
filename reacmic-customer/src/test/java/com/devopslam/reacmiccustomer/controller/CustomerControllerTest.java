package com.devopslam.reacmiccustomer.controller;

import com.devopslam.reacmiccustomer.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    private final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);

    private WebClient webClient;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        this.webClient = WebClient.create("http://localhost:" + port);
    }

    @Test
    public void getCustomerAccounts() {
        Customer customer = this.webClient.get().uri("/customer/accounts/5aa3852aaf69be087c9343cc")
                .accept(MediaType.APPLICATION_JSON)
                .exchange().flatMap(response -> response.bodyToMono(Customer.class)).block();
        logger.info("Customer: " + customer.toString());
    }

    @Test
    public void addCustomer() {
        Customer customer = new Customer("Adam", "Kowalski", "5aa3852aaf69be087c9343cc");
        customer = webClient.post().uri("/customer").accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(customer))
                .exchange()
                .flatMap(response -> response.bodyToMono(Customer.class)).block();
        logger.info("Customer: " + customer.toString());
    }

}