package uz.pdp.app6annotationandcascade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.app6annotationandcascade.entity.Address;
import uz.pdp.app6annotationandcascade.entity.Person;
import uz.pdp.app6annotationandcascade.payload.AddressDTO;
import uz.pdp.app6annotationandcascade.repository.AddressRepository;
import uz.pdp.app6annotationandcascade.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;

    @PostMapping
    public HttpEntity<?> addAddress(@RequestBody List<AddressDTO> addressDTOList){

        List<Address> addresses = new ArrayList<>();

        for (AddressDTO addressDTO : addressDTOList) {
            Address address = new Address(
                    addressDTO.getStreet(),
                    addressDTO.getCity(),
                    personRepository.getOne(addressDTO.getPersonId())
            );
            addresses.add(address);
        }

        addressRepository.saveAll(addresses);

        return ResponseEntity.ok("Addresses saved!");
    }

}
