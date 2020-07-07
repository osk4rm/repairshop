package com.wsiiz.repairshop.vehicles.domain;

import com.wsiiz.repairshop.foundation.domain.AbstractFactory;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory implements AbstractFactory<Vehicle> {

    @Override
    public Vehicle create() {
        return new Vehicle();
    }
}
