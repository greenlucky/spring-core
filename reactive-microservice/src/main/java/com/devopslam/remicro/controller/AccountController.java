package com.devopslam.remicro.controller;

import com.devopslam.remicro.persistence.model.Account;
import com.devopslam.remicro.persistence.repository.AccountRepository;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {
    
    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountRepository repository;

    @GetMapping("/account/customer/{customerId}")
    public Flux<Account> findByCustomer(@PathVariable("customerId") String customerId) {
        logger.info("findByCustomer: " + customerId);
        return repository.findByCustomerId(customerId)
                .map(a -> new Account(a.getId(), a.getNumber(), a.getCustomerId(), a.getAmount()));
    }

    @GetMapping("/account")
    public Flux<Account> findAll() {
        return repository.findAll().map(a -> new Account(a.getId(), a.getNumber(), a.getCustomerId(), a.getAmount()));
    }

    @GetMapping("/account/{id}")
    public Mono<Account> findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(a -> new Account(a.getId(), a.getNumber(), a.getCustomerId(), a.getAmount()));
    }

    @PostMapping("/account")
    public Mono<Account> create(@RequestBody Publisher<Account> account) {
        return repository.save(Mono.from(account)
                .map(a -> new Account(a.getNumber(), a.getCustomerId(), a.getAmount()))
                .map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount())));
    }
}
