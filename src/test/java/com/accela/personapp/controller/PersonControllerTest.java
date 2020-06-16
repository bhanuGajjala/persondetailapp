package com.accela.personapp.controller;

import com.accela.personapp.controller.dto.PersonRequest;
import com.accela.personapp.controller.dto.PersonResponse;
import com.accela.personapp.service.PersonService;
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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;

    @Test
    public void testHomePage_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(mvcResult.getModelAndView().getViewName(),"index");
    }

    @Test
    public void testAllPersons_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons");
        when(personService.getPersons()).thenReturn(new ArrayList<>());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(mvcResult.getModelAndView().getViewName(),"persons");
    }

    @Test
    public void testAddPersonView_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/addperson");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(mvcResult.getModelAndView().getViewName(),"addperson");
    }

    @Test
    public void testSavePerson_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/person").accept(
                MediaType.APPLICATION_JSON);
        when(personService.savePerson(PersonRequest.builder().firstName("Bhanu").lastName("Gajjala").build()))
                .thenReturn(1L);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.FOUND.value());
        assertEquals(mvcResult.getResponse().getRedirectedUrl(),"/person/0/addressform");
    }

    @Test
    public void testGetPerson_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/1");
        PersonResponse person = PersonResponse.builder().id(1l).firstName("Bhanu").lastName("Gajjala").build();
        when(personService.getPerson(1l))
                .thenReturn(person);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(mvcResult.getModelAndView().getViewName(),"personview");
    }

    @Test
    public void testUpdatePerson_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/person/update/1").accept(
                MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.FOUND.value());
        assertEquals(mvcResult.getResponse().getRedirectedUrl(),"/person/"+1l);
    }

    @Test
    public void testDelete_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/person/delete/1");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.FOUND.value());
        assertEquals(mvcResult.getResponse().getRedirectedUrl(),"/persons");
    }
}
