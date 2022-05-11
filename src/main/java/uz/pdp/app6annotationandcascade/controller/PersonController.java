package uz.pdp.app6annotationandcascade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app6annotationandcascade.entity.Address;
import uz.pdp.app6annotationandcascade.entity.Person;
import uz.pdp.app6annotationandcascade.payload.AddressDTO;
import uz.pdp.app6annotationandcascade.payload.PersonDTO;
import uz.pdp.app6annotationandcascade.repository.AddressRepository;
import uz.pdp.app6annotationandcascade.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

/*
*
* app.clickup.com
* */

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    /**
     *@Transactional  --> Agar amal ohiriga yetmasa xatolikka uchrasa bu annotatsiya bajarilgan amalarni orqaga qaytaradi
     * noRollbackFor = ArithmeticException.class --> ArithmeticException bo'lsa orqaga qaytarma
     * rollbackFor = NullPointerException.class  -->  NullPointerException bo'lsa orqaga qaytar
     */
    @Transactional(noRollbackFor = ArithmeticException.class, rollbackFor = NullPointerException.class)
    @PostMapping
    public HttpEntity<?> addPerson(@RequestBody PersonDTO personDTO){

        Person person = new Person();
        person.setFullName(personDTO.getFullName());

        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : personDTO.getAddressDTOList()) {
            Address address = new Address(
                    addressDTO.getStreet(),
                    addressDTO.getCity(),
                    person
            );
            addresses.add(address);
        }

        person.setAddresses(addresses);
        personRepository.save(person);

//        String var = null;
//        boolean test = var.equals("test");

        return ResponseEntity.ok("Person saved!");
    }
    /*
    * __PUT
    * */
    @PutMapping("/{id}")
    public HttpEntity<?> editPerson(@PathVariable Integer id){

        Person person = personRepository.getOne(id);
        person.setFullName("ism o'zgardi");

        List<Address> addresses = new ArrayList<>();
        for (Address address : person.getAddresses()) {
            address.setStreet("Ko'cha nomi oz'gartirildi");
        }

        personRepository.save(person);

        return ResponseEntity.ok("Person saved!");
    }
    /*
    * __DELETE
    * */
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePerson(@PathVariable Integer id){
        try {
            personRepository.deleteById(id);
            return ResponseEntity.ok("Deleted!");
        }catch (Exception e){
            return ResponseEntity.ok("Not Deleted!");
        }
    }




    @DeleteMapping("/forTransaction/{id}")
    public HttpEntity<?> deleteForTransactional(@PathVariable Integer id){
        personRepository.deleteById(id);
        throw new NullPointerException();
//        return ResponseEntity.ok("o'chirildi");
    }



    @GetMapping
    public HttpEntity<?> getPeople(){
        return ResponseEntity.ok(personRepository.findAll());
    }

}
