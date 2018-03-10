package com.devopslam.reacmiccustomer.repository;

import com.devopslam.reacmiccustomer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class CustomerRepository {

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<Customer> findById(String id) {
        return template.findById(id, Customer.class);
    }

    public Flux<Customer> findAll() {
        return template.findAll(Customer.class);
    }

    public Mono<Customer> findByPesel(String pesel) {
        return template.findOne(Query.query(where("pesel").is(pesel)), Customer.class);
    }

    public Mono<Customer> save(Mono<Customer> customer) {
        return template.insert(customer);
    }

}
