package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.service.ServiceService;
import com.wsiiz.repairshop.servicing.domain.service.Task;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;


public class MaintenanceCard implements I18nAware {

    Service service;
    ServiceService serviceService;

    public MaintenanceCard(Service service, UI ui, ServiceService serviceService) {
        this.service = service;
        this.serviceService = serviceService;

        Window window = new Window(i18n("tasksWindow"));
        window.setWidth("50%");
        window.center();


        window.setContent(new MVerticalLayout(infoText(), createTasksGrid()));


        ui.addWindow(window);
    }

    protected RichText infoText(){

        RichText info = new RichText();

        info.setRichText(("Karta serwisowa pojazdu: ") + serviceService.getVehicleData(service.getVehicleId()));

        return info;
    }

    protected MGrid<Task> createTasksGrid(){

        MGrid<Task> tasks = new MGrid<>();

        tasks.setRows(service.getTasks());

        tasks.addColumn(e -> e.getDescription()).setCaption(i18n("taskDescription"));
        tasks.addColumn(e -> e.getResponsiblePerson()).setCaption(i18n("employee"));
        tasks.addColumn(e -> e.getStatus()).setCaption(i18n("status"));

        tasks.setWidthFull();
        tasks.setStyleName("tasksTable");

        return tasks;
    }

}




