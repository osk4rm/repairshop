package com.wsiiz.repairshop.customers.application;

import com.wsiiz.repairshop.customers.domain.customer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/customers/companies")
    public ResponseEntity<List<Company>> getMany(

            @RequestParam(value = "name", required = false) String name
    ) {
        return ResponseEntity.ok(companyRepository.findByCriteria(name));
    }

    @GetMapping("/customers/companies/{id}")
    public ResponseEntity<Company> getOne(@PathVariable("id") Long id) {

        Optional<Company> service = companyRepository.findById(id);

        if (service.isPresent()) {
            return ResponseEntity.ok(service.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customers/companies")
    public ResponseEntity<Customer> addNew(@RequestBody Company company) {
        return ResponseEntity.created(null).body(companyRepository.save(company));
    }

    @DeleteMapping("/customers/companies/{id}")
    public ResponseEntity<Customer> remove(@PathVariable("id") Long id) {

        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()) {
            companyRepository.deleteById(id);
            return ResponseEntity.ok(company.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}