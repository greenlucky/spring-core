package com.devopslam.reacmiccustomer.model;

import com.devopslam.services.common.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Customer extends com.devopslam.services.common.Customer {

    public Customer() {
    }

    public Customer(String id, String firstName, String lastName, String pesel) {
        super(id, firstName, lastName, pesel);
    }

    public Customer(String firstName, String lastName, String pesel) {
        super(firstName, lastName, pesel);
    }

    public Customer(String pesel, List<Account> accounts) {
        super(pesel, accounts);
    }

    @Id
    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
