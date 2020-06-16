package com.accela.personapp.service;

import com.accela.personapp.controller.dto.PersonRequest;
import com.accela.personapp.controller.dto.PersonResponse;
import com.accela.personapp.controller.dto.UpdatePersonRequest;
import com.accela.personapp.dao.PersonRepository;
import com.accela.personapp.dao.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PersonServiceImplTest {

    private List<Person> personList;

    @Mock
    PersonRepository mockRepository;

    private PersonService personService;

    @BeforeEach
    public void setUp() {
        personService = new PersonServiceImpl();
        ReflectionTestUtils.setField(personService, "personRepository",  mockRepository);
        personList =buildPersons();
    }

    private List<Person> buildPersons(){
        return Arrays.asList(Person.builder().id(1L).firstName("Bhanu").lastName("Gajjala").build(),
                Person.builder().id(2l).firstName("Bhanu1").lastName("Gajjala1").build());
    }

    @Test
    public void showAllPersonsTest(){
        when(mockRepository.findAll()).thenReturn(personList);
        List<PersonResponse> dbPersonList = personService.getPersons();
        assertEquals(personList.size(), dbPersonList.size());
    }


    @Test
    public void findPersonByIdTest(){
        when(mockRepository.findById(1L)).thenReturn(Optional.ofNullable(personList.get(0)));
        PersonResponse dbPerson = personService.getPerson(1L);
        assertEquals(dbPerson.getFirstName(), personList.get(0).getFirstName());
    }

    @Test
    public void savePersonsTest(){
        PersonRequest personRequest = PersonRequest.builder().firstName("Bhanu").lastName("Gajjala").build();
        Person person = Person.builder().firstName("Bhanu").lastName("Gajjala").build();
        when(mockRepository.save(person)).thenReturn(personList.get(0));
        personService.savePerson(personRequest);
        verify(mockRepository,times(1)).save(person);
    }

    @Test
    public void editPersonTest() {
        UpdatePersonRequest personRequest = UpdatePersonRequest.builder().firstName("Bhanu").lastName("Gajjala").build();
        when(mockRepository.save(personList.get(0))).thenReturn(personList.get(0));
        personService.updatePerson(1L, personRequest);
        verify(mockRepository,times(1)).save(personList.get(0));
    }
    @Test
    public void deletePersonTest(){
        doNothing().when(mockRepository).deleteById(1L);
        personService.deletePerson(1L);
        verify(mockRepository,times(1)).deleteById(1L);
    }

    //Due to less time skipping few test cases.
}
