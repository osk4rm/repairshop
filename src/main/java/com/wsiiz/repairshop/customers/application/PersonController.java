package com.wsiiz.repairshop.customers.application;

import com.wsiiz.repairshop.customers.domain.customer.Customer;
import com.wsiiz.repairshop.customers.domain.customer.CustomerRepository;
import com.wsiiz.repairshop.customers.domain.customer.Person;
import com.wsiiz.repairshop.customers.domain.customer.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/customers/persons")
    public ResponseEntity<List<Person>> getMany(
            @RequestParam(value = "pesel", required = false) String pesel,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname) {
        return ResponseEntity.ok(personRepository.findByCriteria(pesel, name, surname));
    }

    @GetMapping("/customers/persons/{id}")
    public ResponseEntity<Person> getOne(@PathVariable("id") Long id) {

        Optional<Person> service = personRepository.findById(id);

        if (service.isPresent()) {
            return ResponseEntity.ok(service.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customers/persons")
    public ResponseEntity<Customer> addNew(@RequestBody Person person) {
        return ResponseEntity.created(null).body(personRepository.save(person));
    }

    @DeleteMapping("/customers/persons/{id}")
    public ResponseEntity<Customer> remove(@PathVariable("id") Long id) {

        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}