package com.devopslam.remicro.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Setter
@Getter
public class Account extends com.devopslam.services.common.Account {

    public Account() {
    }

    public Account(String number, String customerId, int amount) {
        super(number, customerId, amount);
    }

    public Account(String id, String number, String customerId, int amount) {
        super(id, number, customerId, amount);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Id
    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
