package com.devopslam.segapattern.persistance.model;

import java.util.List;

public class DeliveryInformation {

    private DeliveryAddress address;
    private Long lastAddress;

    public DeliveryInformation() {
    }

    public DeliveryInformation(DeliveryAddress address, Long lastAddress) {
        this.address = address;
        this.lastAddress = lastAddress;
    }

    public DeliveryAddress getAddress() {
        return address;
    }

    public void setAddress(DeliveryAddress address) {
        this.address = address;
    }

    public Long getLastAddress() {
        return lastAddress;
    }

    public void setLastAddress(Long lastAddress) {
        this.lastAddress = lastAddress;
    }

    @Override
    public String toString() {
        return "DeliveryInformation{" +
                "address=" + address +
                ", lastAddress=" + lastAddress +
                '}';
    }
}
