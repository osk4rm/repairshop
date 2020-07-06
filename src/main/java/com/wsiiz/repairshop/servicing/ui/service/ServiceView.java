package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.wsiiz.repairshop.foundation.ui.BaseView;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.service.ServiceFactory;
import com.wsiiz.repairshop.servicing.domain.service.ServiceRepository;
import com.wsiiz.repairshop.servicing.domain.service.ServiceService;
import org.vaadin.viritin.grid.MGrid;

@SpringComponent
@UIScope
@SpringView
public class ServiceView extends BaseView<Service> {

  public ServiceView(ServiceFactory serviceFactory, ServiceService service,
      ServiceRepository serviceRepository) {
    super(serviceFactory, service, serviceRepository, new ServiceEditor());
  }

  @Override
  protected void addColumns(MGrid<Service> table) {

    table.addColumn(entity -> entity.getDescription())
        .setCaption(i18n("description"));

    table.addColumn(entity -> entity.getRegistrationTime(), new LocalDateTimeRenderer())
        .setCaption(i18n("registrationTime"));
  }

}
