package com.accela.personapp.service;

import com.accela.personapp.controller.dto.AddressRequest;
import com.accela.personapp.controller.dto.UpdateAddressRequest;
import com.accela.personapp.dao.domain.Address;
import javassist.NotFoundException;

import java.util.List;

public interface AddressService {

    public void saveAddress(Long personId, AddressRequest addresses);
    public void updateAddress(Long personId, Long addressId, UpdateAddressRequest updateAddressRequest) throws NotFoundException;
    public List<Address> getAddresses(Long personId);
    public void delete(Long addressId);
    public Address getAddress(Long addressId);
}
