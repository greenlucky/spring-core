package com.devopslam.segapattern.persistance.model;

public class Address {
    private String receivedName;
    private String address;
    private String city;
    private String location;

    public Address() {
    }

    public String getReceivedName() {
        return receivedName;
    }

    public void setReceivedName(String receivedName) {
        this.receivedName = receivedName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Address{" +
                "receivedName='" + receivedName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
