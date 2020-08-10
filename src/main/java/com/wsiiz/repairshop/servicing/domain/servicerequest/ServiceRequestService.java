package com.wsiiz.repairshop.servicing.domain.servicerequest;

import com.wsiiz.repairshop.foundation.domain.AbstractService;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.service.ServiceRepository;
import com.wsiiz.repairshop.vehicles.domain.Vehicle;
import com.wsiiz.repairshop.vehicles.domain.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceRequestService implements AbstractService<ServiceRequest> {

  @Autowired
  ServiceRequestRepository serviceRepository;

  @Autowired
  VehicleRepository vehicleRepository;

  @Override
  public ServiceRequest save(ServiceRequest entity) {
    return serviceRepository.save(entity);
  }

  public List<Vehicle> findVehicles(){
    return vehicleRepository.findAll();
  }

  public String getVehicleData(Long id){
    return vehicleRepository.findById(id).map(Vehicle::toString).orElse("");
  }
}

