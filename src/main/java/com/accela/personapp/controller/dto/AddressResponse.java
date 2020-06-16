package com.accela.personapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
}
