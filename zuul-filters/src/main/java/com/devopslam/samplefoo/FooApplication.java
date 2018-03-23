package com.devopslam.samplefoo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FooApplication {

    @GetMapping("/foo")
    public String foo() {
        return "foo from " + getClass().getSimpleName();
    }

    @PostMapping("/foo")
    public String foo(@RequestBody String body) {
        return "foo received: " + body;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello from " + getClass().getSimpleName();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(FooApplication.class)
                .properties("server.port=9080", "spring.application.name=foo")
                .run(args);
    }
}
