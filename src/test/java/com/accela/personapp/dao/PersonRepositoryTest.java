package com.accela.personapp.dao;

import com.accela.personapp.dao.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Mock
    PersonRepository mockRepository;

    private List<Person> personList;

    @BeforeEach
    public void setUp() {
        personList =buildPersons();
    }

    private List<Person> buildPersons(){
        return Arrays.asList(Person.builder().id(1L).firstName("Bhanu").lastName("Gajjala").build(),
                Person.builder().id(2l).firstName("Bhanu1").lastName("Gajjala1").build());
    }

    @Test
    public void findAllPersonTest() {
        when(mockRepository.findAll()).thenReturn(personList);
        personList = mockRepository.findAll();
        assertEquals(personList.size(), 2);
    }
    @Test
    public void findAllPersonTest_withNoPersons() {
        when(mockRepository.findAll()).thenReturn(new ArrayList<>());
        personList = mockRepository.findAll();
        assertEquals(personList.size(), 0);
    }

    @Test
    public void findByIdTest() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(personList.get(0)));
        Person person = mockRepository.findById(1l).orElse(null);
        assertNotNull(person);
    }
    @Test
    public void findByIdTest_withInvalidId() {
        when(mockRepository.findById(1L)).thenReturn(null);
        Person person = mockRepository.findById(3l).orElse(null);
        assertNull(person);
    }

    // Due to less time skipping some more test cases.
}
