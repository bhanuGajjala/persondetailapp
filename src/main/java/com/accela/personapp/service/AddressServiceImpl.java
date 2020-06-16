package com.accela.personapp.service;

import com.accela.personapp.controller.dto.AddressRequest;
import com.accela.personapp.controller.dto.UpdateAddressRequest;
import com.accela.personapp.dao.AddressRepository;
import com.accela.personapp.dao.domain.Address;
import com.accela.personapp.dao.domain.Person;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public void saveAddress(Long personId, AddressRequest addressRequest) {

        Address address = Address.builder()
                    .city(addressRequest.getCity())
                    .state(addressRequest.getState())
                    .street(addressRequest.getStreet())
                    .postalCode(addressRequest.getPostalCode())
                .person(Person.builder().id(personId).build()).build();

        addressRepository.save(address);

    }

    @Override
    public void updateAddress(Long personId, Long addressId, UpdateAddressRequest updateAddressRequest) throws NotFoundException {
        Address address = addressRepository.findById(addressId).orElse(null);

        if(address != null){
            address.setCity(updateAddressRequest.getCity());
            address.setState(updateAddressRequest.getState());
            address.setStreet(updateAddressRequest.getStreet());
            address.setPostalCode(updateAddressRequest.getPostalCode());
            addressRepository.save(address);
        } else {
            throw new NotFoundException("Address Not Found");
        }
    }

    @Override
    public List<Address> getAddresses(Long personId) {
        return addressRepository.findAllByPersonId(personId);
    }

    @Override
    public void delete(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public Address getAddress(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

}
