package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.service.ServiceRepository;
import com.wsiiz.repairshop.servicing.domain.service.Status;
import com.wsiiz.repairshop.servicing.domain.service.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@SpringComponent
@SpringUI
@UIScope
public class MaintenanceCard extends AbstractForm<Service> implements I18nAware {

    ServiceRepository repository;
    DateTimeField registrationTime = new DateTimeField();
    CheckBox requiresWashing = new CheckBox();
    TextField description = new TextField();

    MGrid<Service> tasks = new MGrid<>();

    @Autowired
    public MaintenanceCard(ServiceRepository repository) {
        super(Service.class);
        this.repository = repository;
    }

    @Override
    protected Component createContent() {

        return new MVerticalLayout(new RichText("open tasks"),
                new MHorizontalLayout(createOpenedServices(tasks))
        ).withStyleName("with-small-frame");
    }

    protected MGrid<Service> createOpenedServices(MGrid<Service> tasks){

        MGrid<Service> table = tasks;

        table.setRows(repository.findAll().stream().filter(f -> f.getStatus().equals(Status.OPENED)).collect(Collectors.toList()));
        table.addColumn(e -> e.getTasks().stream().map(Task::getDescription).collect(Collectors.toList())).setCaption("DESCRIPTION");
        table.addColumn(e -> e.getTasks().stream().map(Task::getResponsiblePerson).collect(Collectors.toList())).setCaption("EMPLOYEE");
        table.addColumn(e -> e.getRequiresWashing()).setCaption("WASHING");
        table.addColumn(e -> e.getRegistrationTime().format(DateTimeFormatter.ISO_DATE_TIME)).setCaption("REG TIME");

        table.setHeightFull();
        table.setWidthFull();

        return table;
    }

    @Override
    public Window openInModalPopup() {
        Window w = super.openInModalPopup();
        w.setHeight("95%");
        w.setWidth("70%");
        return w;
    }
}
