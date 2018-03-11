package com.devopslam.reactivetweet.persistence.repository;

import com.devopslam.reactivetweet.persistence.model.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {

}
