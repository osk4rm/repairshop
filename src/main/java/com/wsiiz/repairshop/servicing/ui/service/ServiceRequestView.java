package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.vaadin.ui.themes.ValoTheme;
import com.wsiiz.repairshop.foundation.ui.BaseView;
import com.wsiiz.repairshop.foundation.ui.dialog.ConfirmDialog;
import com.wsiiz.repairshop.servicing.domain.servicerequest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.layouts.MHorizontalLayout;

@SpringComponent
@UIScope
@SpringView
public class ServiceRequestView extends BaseView<ServiceRequest> {

  @Autowired
  ServiceRequestService service;

  public ServiceRequestView(ServiceRequestFactory serviceRequestFactory, ServiceRequestService service,
                            ServiceRequestRepository serviceRepository) {
    super(serviceRequestFactory, service, serviceRepository, new ServiceRequestEditor(service));
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

  @Override
  protected MGrid<ServiceRequest> createTable() {
    MGrid<ServiceRequest> table = new MGrid<>();

    addColumns(table);

    table.addComponentColumn(entity -> new MHorizontalLayout(
            new MButton(VaadinIcons.COG, e -> {
              // todo later
            }).withStyleName("no-padding").withStyleName(ValoTheme.BUTTON_BORDERLESS),
            new MButton(VaadinIcons.EDIT, e -> {
              editInPopup(entity);
            }).withStyleName(ValoTheme.BUTTON_BORDERLESS).withStyleName("no-padding"),
            new MButton(VaadinIcons.TRASH, e -> {
              new ConfirmDialog(i18n("deleteConfirmation"), () -> {
                repository.delete(entity);
                loadEntities();
              }, getUI());
            }).withStyleName(ValoTheme.BUTTON_BORDERLESS).withStyleName("no-padding")))
            .setCaption(i18n(BaseView.class, "actions"))
            .setWidth(120);

    table.setHeightFull();
    table.setWidthFull();

    return table;
  }
}
