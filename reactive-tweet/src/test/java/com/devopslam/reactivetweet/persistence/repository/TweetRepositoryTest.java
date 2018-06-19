package com.devopslam.reactivetweet.persistence.repository;

import com.devopslam.reactivetweet.persistence.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TweetRepositoryTest {

    @Autowired
    private TweetRepository repository;

    @Test
    public void create() {
        Tweet tweet = new Tweet("Lam Nguyen");
        Mono<Tweet> tweetMono = repository.save(tweet);
        StepVerifier
                .create(tweetMono)
                .assertNext(tweet1 -> {
                    assertNotNull(tweet1.getId());
                    assertNotNull(tweet1.getText());
                })
                .expectComplete()
                .verify();
    }

}