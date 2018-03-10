package com.devopslam.remicro.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class Application {

    private static final String MONGO_URL = "mongodb://127.0.0.1";

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(MONGO_URL);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient(), "test");
    }
}
