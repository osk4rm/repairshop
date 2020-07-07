package com.wsiiz.repairshop.vehicles.domain;

import com.wsiiz.repairshop.foundation.domain.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleService implements AbstractService<Vehicle> {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle entity) {
        return vehicleRepository.save(entity);
    }
}
