package com.accela.personapp.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PersonRequest {

    private Long personId;
    private String firstName;
    private String lastName;
}
