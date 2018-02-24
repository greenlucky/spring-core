package com.devopslam.segapattern.persistance.model;

import com.devopslam.segapattern.persistance.model.inf.ISeqaOrchesSeq;

import java.util.Objects;
import java.util.UUID;

public class Order {

    private Long id;
    private String name;
    private String orderId;

    private ISeqaOrchesSeq current;

    public Order(String name) {
        this.name = name;
        this.orderId = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public ISeqaOrchesSeq getCurrent() {
        return current;
    }

    public void setCurrent(ISeqaOrchesSeq current) {
        this.current = current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderId='" + orderId + '\'' +
                ", current=" + current +
                '}';
    }
}
