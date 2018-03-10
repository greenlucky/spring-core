package com.devopslam.remicro.persistence.repository;

import com.devopslam.remicro.persistence.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class AccountRepository {

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Account> findById(String id) {
        return template.findById(id, Account.class);
    }

    public Flux<Account> findAll() {
        return template.findAll(Account.class);
    }

    public Flux<Account> findByCustomerId(String customerId) {
        return template.find(query(where("customerId").is(customerId)), Account.class);
    }

    public Mono<Account> save(Mono<Account> account) {
        return template.insert(account);
    }
}
