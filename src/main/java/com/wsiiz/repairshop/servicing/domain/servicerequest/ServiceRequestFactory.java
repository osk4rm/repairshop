package com.wsiiz.repairshop.servicing.domain.servicerequest;

import org.springframework.stereotype.Component;

@Component
public class ServiceRequestFactory {

  public ServiceRequest create() {
    return new ServiceRequest();
  }

}
