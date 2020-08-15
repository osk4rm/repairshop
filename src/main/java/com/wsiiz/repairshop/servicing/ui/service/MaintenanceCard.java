package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.ui.*;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class MaintenanceCard extends AbstractForm<Service> implements I18nAware {

    DateTimeField registrationTime = new DateTimeField();
    CheckBox requiresWashing = new CheckBox();
    TextField description = new TextField();

    MGrid<Service> tasks = new MGrid<>();

    public MaintenanceCard() {
        super(Service.class);
    }

    @Override
    protected Component createContent() {


        return new MVerticalLayout(new RichText("open tasks"),
                new MHorizontalLayout()
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
