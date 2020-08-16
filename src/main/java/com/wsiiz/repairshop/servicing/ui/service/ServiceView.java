package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.vaadin.ui.themes.ValoTheme;
import com.wsiiz.repairshop.foundation.ui.BaseView;
import com.wsiiz.repairshop.foundation.ui.dialog.ConfirmDialog;
import com.wsiiz.repairshop.servicing.domain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import sun.rmi.rmic.Main;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
@SpringView
public class ServiceView extends BaseView<Service> {

  @Autowired
  ServiceService service;


//  private final AbstractForm<Service> mCard;

  public ServiceView(ServiceFactory factory, ServiceService service,
                     ServiceRepository repository) {
    super(factory, service, repository, new ServiceEditor(service));
//    this.mCard = new MaintenanceCard(service);
  }

//  protected void showMCard(Service entity) {
//
//      mCard.setEntity(entity);
//      mCard.openInModalPopup();
//  }


  @Override
  protected void addColumns(MGrid<Service> table) {

    table.addColumn(entity -> service.getVehicleData(entity.getVehicleId()))
            .setCaption(i18n("vehicle"));

    table.addColumn(entity -> i18n(RequestType.class, entity.getRequestType().name()))
            .setCaption(i18n("requestType"));

    table.addColumn(entity -> entity.getDescription())
        .setCaption(i18n("description"));

    table.addColumn(entity -> entity.getRegistrationTime(), new LocalDateTimeRenderer())
        .setCaption(i18n("registrationTime"));

    table.addColumn(entity -> entity.getStatus())
            .setCaption(i18n("status"));
  }

  @Override
  protected MGrid<Service> createTable() {
    MGrid<Service> table = new MGrid<>();

    addColumns(table);

    table.addComponentColumn(entity -> new MHorizontalLayout(
            new MButton(VaadinIcons.COG, e -> {
              new ConfirmDialog(i18n("setAsCompletedConfirmation"), () -> {
                entity.setStatus(Status.COMPLETED);
                repository.save(entity);
                loadEntities();
              },getUI());

             }).withStyleName(ValoTheme.BUTTON_BORDERLESS).withStyleName("no-padding"),

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