package com.devopslam.samplebar;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BarApplication {

    @GetMapping("/foo")
    public String foo() {
        return "bar from " + getClass().getSimpleName();
    }

    @PostMapping("/foo")
    public String foo(@RequestBody String body) {
        return "bar received: " + body;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(BarApplication.class)
                .properties("server.port=7080", "spring.application.name=bar")
                .run(args);
    }
}
