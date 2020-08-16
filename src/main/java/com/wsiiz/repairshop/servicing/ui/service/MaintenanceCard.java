package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.ui.*;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.service.ServiceService;
import com.wsiiz.repairshop.servicing.domain.service.Task;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;


public class MaintenanceCard extends AbstractForm<Service> implements I18nAware {


    ServiceService service;

    DateTimeField registrationTime = new DateTimeField();
    CheckBox requiresWashing = new CheckBox();
    TextField description = new TextField();

    Grid<Task> tasks = new Grid<>();

    public MaintenanceCard(ServiceService service) {
        super(Service.class);
        this.service = service;

    }

    @Override
    protected Component createContent() {
        tasks.setItems(service.getAllTasks());
        tasks.addColumn(e -> e.getService().getId()).setCaption("ID");
        tasks.addColumn(e -> e.getDescription()).setCaption("DESC");
        tasks.addColumn(e -> e.getResponsiblePerson()).setCaption("EMPLOYEE");

        return new MVerticalLayout(new RichText(getEntity().getVehicleId().toString()),
                new MHorizontalLayout(tasks)
        ).withStyleName("with-small-frame");
    }


    @Override
    public Window openInModalPopup() {
        Window w = super.openInModalPopup();
        w.setHeight("95%");
        w.setWidth("70%");

        return w;
    }
}
