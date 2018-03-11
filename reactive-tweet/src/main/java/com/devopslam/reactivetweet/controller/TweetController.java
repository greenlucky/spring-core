package com.devopslam.reactivetweet.controller;

import com.devopslam.reactivetweet.persistence.model.Tweet;
import com.devopslam.reactivetweet.persistence.repository.TweetRepository;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


@RestController
public class TweetController {

    private final Logger logger = LoggerFactory.getLogger(TweetController.class);

    @Autowired
    private TweetRepository repository;

    @GetMapping("/tweets")
    public Flux<Tweet> getAll() {
        return repository.findAll();
    }

    @GetMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(t -> ResponseEntity.ok(t))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> update(@PathVariable String id,
                                              @Valid @RequestBody Publisher<Tweet> tweet) {
        return repository.findById(id)
                .flatMap(existingTweet -> {
                    existingTweet.setText(Mono.from(tweet).block().getText());
                    return repository.save(existingTweet);
                }).map(updatedTweet -> ResponseEntity.ok(updatedTweet))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/tweets/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return repository.findById(id)
                .flatMap(repository::delete)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> streamAll() {
        return repository.findAll().delayElements(Duration.of(1, ChronoUnit.SECONDS));
    }

    @PostMapping("/tweets")
    public Mono<Tweet> create(@RequestBody Publisher<Tweet> tweet) {
        Tweet local = Mono.from(tweet).block();
        return repository.save(local);
    }
}
