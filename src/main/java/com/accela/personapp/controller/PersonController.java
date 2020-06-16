package com.accela.personapp.controller;

import com.accela.personapp.controller.dto.AddressRequest;
import com.accela.personapp.controller.dto.PersonRequest;
import com.accela.personapp.controller.dto.PersonResponse;
import com.accela.personapp.controller.dto.UpdatePersonRequest;
import com.accela.personapp.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping(value = "/" )
    public String homePage(){
        log.info("Home page");
        return "index";
    }

    @GetMapping(value = "/persons" )
    public String getAllPersion(Model model){
        log.info("List All persons");
        List<PersonResponse> persons = personService.getPersons();
        model.addAttribute("persons", persons);
        return "persons";
    }

    @GetMapping(value = "/addperson" )
    public String addPerson(Model model){
        log.info("Add Person");
        model.addAttribute("personRequest", PersonRequest.builder().build());
        return "addperson";
    }

    @GetMapping(value = "/person/{personId}/address" )
    public String addAddress(@PathVariable String personId,  Model model){
        log.info("Add Address for personId={}", personId);
        model.addAttribute("personRequest", AddressRequest.builder().build());
        return "addaddress";
    }

    @PostMapping(value = "/person")
    public String savePersion(PersonRequest personRequest, Model model){
        log.info("Add Person for personRequest={}", personRequest);
        Long personId = personService.savePerson(personRequest);
        model.addAttribute(personId);
        return "redirect:/person/"+personId+"/addressform";
    }

    @GetMapping(value = "/person/{personId}")
    public String getPerson(@PathVariable Long personId, Model model){
        log.info("Get Person of personId={}", personId);
        PersonResponse personResponse = personService.getPerson(personId);
        model.addAttribute(personResponse);
        model.addAttribute("updatePersonRequest", UpdatePersonRequest.builder()
                .firstName(personResponse.getFirstName()).lastName(personResponse.getLastName()).build());
        return "personview";
    }

    @PostMapping(value = "/person/update/{personId}")
    public String updatePerson(@PathVariable Long personId, UpdatePersonRequest updatePersonRequest, Model model){
        log.info("Update Person of personId={}", personId);
        personService.updatePerson(personId, updatePersonRequest);
        return "redirect:/person/"+personId;
    }

    @GetMapping(value = "/person/delete/{personId}")
    public String deletePerson(@PathVariable Long personId){
        log.info("Delete Person of personId={}", personId);
        personService.deletePerson(personId);
        return "redirect:/persons";
    }

}
