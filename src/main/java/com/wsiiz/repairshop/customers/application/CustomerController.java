package com.wsiiz.repairshop.customers.application;

import com.wsiiz.repairshop.customers.domain.customer.Customer;
import com.wsiiz.repairshop.customers.domain.customer.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired
  CustomerRepository customerRepository;

  @GetMapping("/customers")
  public ResponseEntity<List<Customer>> getMany()
      {
    return ResponseEntity.ok(customerRepository.findAll());
  }

  @GetMapping("/customers/{id}")
  public ResponseEntity<Customer> getOne(@PathVariable("id") Long id) {

    Optional<Customer> service = customerRepository.findById(id);

    if (service.isPresent()) {
      return ResponseEntity.ok(service.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }


  @DeleteMapping("/customers/{id}")
  public ResponseEntity<Customer> remove(@PathVariable("id") Long id) {

    Optional<Customer> customer = customerRepository.findById(id);

    if (customer.isPresent()) {
      customerRepository.deleteById(id);
      return ResponseEntity.ok(customer.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
