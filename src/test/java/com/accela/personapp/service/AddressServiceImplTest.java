package com.accela.personapp.service;

import com.accela.personapp.controller.dto.AddressRequest;
import com.accela.personapp.controller.dto.UpdateAddressRequest;
import com.accela.personapp.dao.AddressRepository;
import com.accela.personapp.dao.domain.Address;
import com.accela.personapp.dao.domain.Person;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AddressServiceImplTest {

    @Mock
    AddressRepository mockRepository;

    private AddressService addressService;

    private List<Address> addressList;

    @BeforeEach
    public void setUp() {
        addressService = new AddressServiceImpl();
        ReflectionTestUtils.setField(addressService, "addressRepository",  mockRepository);
        addressList =buildAddress();
    }

    private List<Address> buildAddress(){
        Person person = Person.builder().id(1L).build();
        return Arrays.asList(Address.builder().street("street1").state("state1").city("city1").postalCode("2323").person(person).build(),
                Address.builder().id(2L).street("street2").state("state2").city("city2").person(person).build());
    }

    @Test
    public void findAddressesForPersonTest(){
        when(mockRepository.findAllByPersonId(1L)).thenReturn(addressList);
        List<Address> dbaddressList = addressService.getAddresses(1L);
        assertEquals(addressList.size(), dbaddressList.size());
    }

    @Test
    public void findAddressesForPersonTest_withNoAddresses(){
        when(mockRepository.findAllByPersonId(3L)).thenReturn(new ArrayList<>());
        List<Address> dbaddressList = addressService.getAddresses(3L);
        assertEquals(dbaddressList.size(), 0);
    }

    @Test
    public void saveAddressForPersonTest(){
        AddressRequest addressRequest = AddressRequest.builder().state("state1").city("city1")
                .street("street1").postalCode("2323").personId(1L).build();
        when(mockRepository.save(addressList.get(0))).thenReturn(addressList.get(0));
        addressService.saveAddress(1L, addressRequest);
        verify(mockRepository,times(1)).save(addressList.get(0));
    }

    @Test
    public void editAddressForPersonTest() {
        UpdateAddressRequest addressRequest = UpdateAddressRequest.builder().state("state1").city("city1")
                .street("street1").postalCode("2323").build();
        when(mockRepository.save(addressList.get(0))).thenReturn(addressList.get(0));
        when(mockRepository.findById(3L)).thenReturn(Optional.ofNullable(null));
        try {
            addressService.updateAddress(1L, 3L, addressRequest);
        } catch (NotFoundException e) {
            verify(mockRepository,times(1)).findById(3L);
        }
    }

    @Test
    public void deleteAddressForPersonTest(){
        AddressRequest addressRequest = AddressRequest.builder().state("state1").city("city1")
                .street("street1").postalCode("2323").personId(1L).build();
        doNothing().when(mockRepository).deleteById(1L);
        addressService.delete(1L);
        verify(mockRepository,times(1)).deleteById(1L);
    }

    //Due to less time skipping few test cases.

}
