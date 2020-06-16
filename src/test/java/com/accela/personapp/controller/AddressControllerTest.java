package com.accela.personapp.controller;

import com.accela.personapp.dao.domain.Address;
import com.accela.personapp.dao.domain.Person;
import com.accela.personapp.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;

    @Test
    public void testCreateAddress_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/person/1/address").accept(
                MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.FOUND.value());
        assertEquals(mvcResult.getResponse().getRedirectedUrl(),"/person/1");
    }

    @Test
    public void testAddressForm_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/person/1/addressform");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(mvcResult.getModelAndView().getViewName(),"addaddress");
    }

    @Test
    public void testEditAddress_success() throws Exception {

        Person person = Person.builder().lastName("Gajjala").firstName("Bhanu").build();
        Address address = Address.builder().postalCode("2323").id(2l).city("city1").street("street1").state("state1").person(person).build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address/2");

        when(addressService.getAddress(2L)).thenReturn(address);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(mvcResult.getModelAndView().getViewName(),"editaddress");
    }

    @Test
    public void testUpdateAddress_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/1/address/2").accept(
                MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.FOUND.value());
        assertEquals(mvcResult.getResponse().getRedirectedUrl(),"/person/1");
    }

    @Test
    public void testDelete_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/person/1/address/delete/2");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.FOUND.value());
        assertEquals(mvcResult.getResponse().getRedirectedUrl(),"/person/1");
    }
}
