package com.accela.personapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class PersonResponse {
    private long id;
    private String firstName;
    private String lastName;
    List<AddressResponse> addressResponses;
}
