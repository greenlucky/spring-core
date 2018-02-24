package com.devopslam.segapattern.controller;

import com.devopslam.segapattern.persistance.model.DeliveryInfoSeq;
import com.devopslam.segapattern.persistance.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/{name}")
    public ResponseEntity<Object> createOrder(@PathVariable String name) {
        Order order = new Order(name);
        order.setCurrent(new DeliveryInfoSeq());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
