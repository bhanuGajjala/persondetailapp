package com.accela.personapp.service;

import com.accela.personapp.controller.dto.AddressResponse;
import com.accela.personapp.controller.dto.PersonRequest;
import com.accela.personapp.controller.dto.PersonResponse;
import com.accela.personapp.controller.dto.UpdatePersonRequest;
import com.accela.personapp.dao.PersonRepository;
import com.accela.personapp.dao.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Long savePerson(PersonRequest personRequest) {

        Person person = Person.builder().firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName()).build();

       Person dbPerson =  personRepository.save(person);

        return dbPerson.getId();

    }

    @Override
    public List<PersonResponse> getPersons() {

        List<Person> persons = personRepository.findAll();

        return persons.stream().map( i ->{
            return PersonResponse.builder().id(i.getId())
                    .firstName(i.getFirstName())
                    .lastName(i.getLastName()).build();

        }).collect(Collectors.toList());
    }

    @Override
    public void updatePerson(Long personId, UpdatePersonRequest updatePersonRequest) {

        Person person = Person.builder().id(personId).firstName(updatePersonRequest.getFirstName())
                .lastName(updatePersonRequest.getLastName()).build();

        personRepository.save(person);

    }

    @Override
    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    @Override
    public Long findPersonCount() {
        return personRepository.count();
    }

    @Override
    public PersonResponse getPerson(Long personId) {

        Person person = personRepository.findById(personId).orElse(null);

        PersonResponse personResponse = null;
        List<AddressResponse> addressResponses = null;

        if(person != null){
            if(person.getAddresses() != null){
                addressResponses = person.getAddresses().stream().map(i ->{

                    return AddressResponse.builder().id(i.getId())
                            .city(i.getCity())
                            .state(i.getState())
                            .postalCode(i.getPostalCode())
                            .build();

                }).collect(Collectors.toList());
            }

           personResponse = PersonResponse.builder()
                    .id(person.getId())
                    .lastName(person.getLastName())
                    .firstName(person.getFirstName())
                    .addressResponses(addressResponses)
                    .build();
        }

        return personResponse;
    }
}
