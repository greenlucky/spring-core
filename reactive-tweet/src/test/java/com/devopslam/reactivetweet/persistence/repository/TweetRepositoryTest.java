package com.devopslam.reactivetweet.persistence.repository;

import com.devopslam.reactivetweet.persistence.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TweetRepositoryTest {

    @Autowired
    private TweetRepository repository;

    @Test
    public void create() {
        Tweet tweet = new Tweet("Lam Nguyen");
        tweet = repository.save(tweet).block();
        System.out.println(tweet.toString());
    }

}