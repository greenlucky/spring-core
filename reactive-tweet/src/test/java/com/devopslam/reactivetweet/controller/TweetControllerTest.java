package com.devopslam.reactivetweet.controller;

import com.devopslam.reactivetweet.persistence.model.Tweet;
import com.devopslam.reactivetweet.persistence.repository.TweetRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TweetControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int port;

    @Autowired
    private TweetRepository repository;

    @Before
    public void init() {
        webTestClient = webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(30000))
                .build();
    }

    @Test
    public void getAll() {

    }

    @Test
    public void getById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void streamAll() {
    }

    @Test
    public void create() {
        Tweet tweet = new Tweet("This is the test Tweet");

        webTestClient.post().uri("/tweets")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(tweet), Tweet.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.text").isEqualTo(tweet.getText());
    }

    @Test
    public void getALl() {
        webTestClient.get()
                .uri("/tweets")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Tweet.class);
    }

    @Test
    public void getOne() {
        Tweet tweet = repository.save(new Tweet("Hello, World!")).block();
        webTestClient.get().uri("/tweets/{id}", Collections.singletonMap("id", tweet.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(repository -> Assertions.assertThat(repository.getResponseBody()).isNotNull());
    }
}