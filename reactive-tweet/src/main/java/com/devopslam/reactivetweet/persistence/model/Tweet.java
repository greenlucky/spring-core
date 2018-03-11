package com.devopslam.reactivetweet.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tweets")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Tweet {

    @Id
    private String id;

    private String text;

    private Date createAt = new Date();

    public Tweet(String text) {
        this.text = text;
    }

    public Tweet(String id, String text, Date createAt) {
        this.id = id;
        this.text = text;
        this.createAt = createAt;
    }
}
