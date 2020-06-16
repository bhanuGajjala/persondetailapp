package com.accela.personapp.controller;

import com.accela.personapp.controller.dto.AddressRequest;
import com.accela.personapp.controller.dto.UpdateAddressRequest;
import com.accela.personapp.dao.domain.Address;
import com.accela.personapp.service.AddressService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping(value = "/person/{personId}/addressform" )
    public String viewAddress(@PathVariable Long personId, Model model){
        log.info("View Address for personID={}", personId);
        model.addAttribute("addressRequest", AddressRequest.builder().build());
        model.addAttribute(personId);
        return "addaddress";
    }

    @PostMapping(value = "/person/{personId}/address" )
    public String saveAddresses(@PathVariable("personId") Long personId, AddressRequest addressRequest, Model model){
        log.info("Save Address for personID={}", personId);
        addressService.saveAddress(personId, addressRequest);
        return "redirect:/person/"+ personId;
    }

    @GetMapping(value = "/address/{addressId}")
    public String editAddress(@PathVariable Long addressId, Model model){
        log.info("Edit Address for addressId={}", addressId);
        Address address = addressService.getAddress(addressId);
        model.addAttribute(address);
        model.addAttribute(UpdateAddressRequest.builder().build());
        return "editaddress";
    }

    @PostMapping(value = "/{personId}/address/{addressId}")
    public String updateAddress(@PathVariable Long personId, @PathVariable Long addressId, UpdateAddressRequest updateAddressRequest){
        log.info("Update Address for addressId={}", addressId);
        try {
            addressService.updateAddress(personId, addressId, updateAddressRequest);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return "redirect:/person/"+ personId;
    }

    @GetMapping(value = "/person/{personId}/address/delete/{addressId}")
    public String deleteAddress(@PathVariable Long personId, @PathVariable Long addressId){
        log.info("Delete Address of addressId={}", addressId);
        addressService.delete(addressId);
        return "redirect:/person/"+ personId;
    }

}
