package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.DescriptionGenerator;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.wsiiz.repairshop.foundation.ui.BaseView;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.service.ServiceFactory;
import com.wsiiz.repairshop.servicing.domain.service.ServiceRepository;
import com.wsiiz.repairshop.servicing.domain.service.ServiceService;
import com.wsiiz.repairshop.servicing.domain.servicerequest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.grid.MGrid;

@SpringComponent
@UIScope
@SpringView
public class ServiceView extends BaseView<ServiceRequest> {

  @Autowired
  ServiceRequestService service;

  public ServiceView(ServiceRequestFactory serviceRequestFactory, ServiceRequestService service,
                     ServiceRequestRepository serviceRepository) {
    super(serviceRequestFactory, service, serviceRepository, new ServiceEditor(service));
  }

  @Override
  protected void addColumns(MGrid<ServiceRequest> table) {

    table.addColumn(entity -> service.getVehicleData(entity.getVehicleId()))
            .setCaption(i18n("vehicle"));

    table.addColumn(entity -> i18n(RequestType.class, entity.getRequestType().name()))
            .setCaption(i18n("requestType"));

    table.addColumn(entity -> entity.getDescription())
        .setCaption(i18n("description"));

    table.addColumn(entity -> entity.getRegistrationTime(), new LocalDateTimeRenderer())
        .setCaption(i18n("registrationTime"));
  }

}
