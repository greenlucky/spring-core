package com.devopslam.restoauth2.persistance.model.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@EqualsAndHashCode(of = "id")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

    public String getName() {
        return name;
    }
}
