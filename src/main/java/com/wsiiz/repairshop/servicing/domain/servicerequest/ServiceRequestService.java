package com.wsiiz.repairshop.servicing.domain.servicerequest;

import com.wsiiz.repairshop.foundation.domain.AbstractService;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.service.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestService implements AbstractService<ServiceRequest> {

  @Autowired
  ServiceRequestRepository serviceRepository;

  @Override
  public ServiceRequest save(ServiceRequest entity) {
    return serviceRepository.save(entity);
  }

}
