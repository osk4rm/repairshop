package com.wsiiz.repairshop.servicing.domain.service;

import com.wsiiz.repairshop.foundation.domain.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceService implements AbstractService<Service> {

  @Autowired
  ServiceRepository serviceRepository;

  @Override
  public Service save(Service entity) {
    return serviceRepository.save(entity);
  }

}
