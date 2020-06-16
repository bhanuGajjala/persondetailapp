package com.accela.personapp.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "personid")
    private Person person;

    public Address(){

    }

}
