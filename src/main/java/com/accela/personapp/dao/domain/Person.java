package com.accela.personapp.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    List<Address> addresses;

    public Person(){

    }
}
