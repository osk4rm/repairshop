package com.wsiiz.repairshop.servicing.domain.service;

import com.wsiiz.repairshop.foundation.domain.AbstractService;
import com.wsiiz.repairshop.vehicles.domain.Vehicle;
import com.wsiiz.repairshop.vehicles.domain.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceService implements AbstractService<Service> {

  @Autowired
  ServiceRepository serviceRepository;

  @Autowired
  VehicleRepository vehicleRepository;

  @Override
  public Service save(Service entity) {
    return serviceRepository.save(entity);
  }

  public List<Vehicle> findVehicles(){
    return vehicleRepository.findAll();
  }

  public String getVehicleData(Long id){
    return vehicleRepository.findById(id).map(Vehicle::toString).orElse("");
  }
}
