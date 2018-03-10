package com.devopslam.reacmiccustomer.controller;

import com.devopslam.reacmiccustomer.model.Customer;
import com.devopslam.reacmiccustomer.repository.CustomerRepository;
import com.devopslam.services.common.Account;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private static final String ACCOUNT_URI = "/account/customer";
    @Autowired
    private WebClient webClient;

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customer/{id}")
    public Mono<Customer> findById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @GetMapping("/customer/accounts/{pesel}")
    public Mono<Customer> findByWithAccount(@PathVariable("pesel") String pesel) {
        logger.info("FindByWithAccount Pesel: {}", pesel);
        return repository.findByPesel(pesel)
                .log()
                .flatMap(customer -> webClient.get().uri(ACCOUNT_URI + "/{customerId}", customer.getId())
                        .accept(MediaType.APPLICATION_JSON).exchange()
                        .log("Founded customer:" + customer.toString())
                        .flatMap(resp -> resp.bodyToFlux(Account.class).collectList()
                                .map(l -> new Customer(pesel, l))));

    }

    @PostMapping("/customer")
    public Mono<Customer> create(@RequestBody Publisher<Customer> customer) {
        logger.info("Create customer");
        return repository.save(Mono.from(customer)
                .map(resp -> new Customer(resp.getFirstName(), resp.getLastName(), resp.getPesel())));
    }


}
