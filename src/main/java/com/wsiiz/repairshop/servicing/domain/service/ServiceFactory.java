package com.wsiiz.repairshop.servicing.domain.service;

import com.wsiiz.repairshop.foundation.domain.AbstractFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory implements AbstractFactory<Service> {

  @Override
  public Service create() {
    Service service = new Service();
    service.setRequiresWashing(true);
    return service;
  }

}