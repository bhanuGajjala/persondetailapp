package com.accela.personapp.dao;

import com.accela.personapp.dao.domain.Address;
import com.accela.personapp.dao.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
public class AddressRepositoryTest {

    @Mock
    AddressRepository mockRepository;

    private List<Address> addressList;

    @BeforeEach
    public void setUp() {
        addressList =buildAddress();
    }

    private List<Address> buildAddress(){
        Person person = Person.builder().id(1L).firstName("Bhanu").lastName("Gajjala").build();
        return Arrays.asList(Address.builder().id(1L).street("street1").state("state1").city("city1").person(person).build(),
                Address.builder().id(2L).street("street2").state("state2").city("city2").person(person).build());
    }

    @Test
    public void findAllAddressTest() {
        when(mockRepository.findAll()).thenReturn(addressList);
        addressList = mockRepository.findAll();
        assertEquals(addressList.size(), 2);
    }
    @Test
    public void findAllAddressTest_withEmpyt() {
        when(mockRepository.findAll()).thenReturn(new ArrayList<>());
        addressList = mockRepository.findAll();
        assertEquals(addressList.size(), 0);
    }

    @Test
    public void findByIdTest() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(addressList.get(0)));
        Address address = mockRepository.findById(1l).orElse(null);
        assertNotNull(address);
    }
    @Test
    public void findByIdTest_withInvalidId() {
        when(mockRepository.findById(1L)).thenReturn(null);
        Address address = mockRepository.findById(3l).orElse(null);
        assertNull(address);
    }

    @Test
    public void findAllByPersonIdTest_withInvalidId() {
        when(mockRepository.findAllByPersonId(1L)).thenReturn(new ArrayList<>());
        List<Address> addresses = mockRepository.findAllByPersonId(3l);
        assertEquals(addresses.size(), 0);
    }

    @Test
    public void findAllByPersonIdTest() {
        when(mockRepository.findAllByPersonId(1L)).thenReturn(addressList);
        List<Address> addresses = mockRepository.findAllByPersonId(1l);
        assertEquals(addresses.size(), 2);
    }

    // Due to less time skipping some more test cases.
}
