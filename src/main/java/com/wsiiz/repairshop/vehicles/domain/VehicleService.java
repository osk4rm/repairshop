package com.wsiiz.repairshop.vehicles.domain;

import com.wsiiz.repairshop.customers.domain.customer.Customer;
import com.wsiiz.repairshop.customers.domain.customer.CustomerRepository;
import com.wsiiz.repairshop.foundation.domain.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleService implements AbstractService<Vehicle> {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Vehicle save(Vehicle entity) {
        return vehicleRepository.save(entity);
    }

    public List<Customer> getCustomersList() {
        return customerRepository.findAll();
    }
}
