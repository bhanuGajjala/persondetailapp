package com.accela.personapp.service;

import com.accela.personapp.controller.dto.PersonRequest;
import com.accela.personapp.controller.dto.PersonResponse;
import com.accela.personapp.controller.dto.UpdatePersonRequest;

import java.util.List;

public interface PersonService {

    public Long savePerson(PersonRequest personRequest);
    public List<PersonResponse> getPersons();
    public void updatePerson(Long personId, UpdatePersonRequest updatePersonRequest);
    public void deletePerson(Long personId);
    public Long findPersonCount();
    public PersonResponse getPerson(Long personId);
}
