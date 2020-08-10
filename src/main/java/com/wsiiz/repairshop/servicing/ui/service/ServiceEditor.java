package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.ui.*;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.servicerequest.RequestType;
import com.wsiiz.repairshop.servicing.domain.servicerequest.ServiceRequest;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class ServiceEditor extends AbstractForm<ServiceRequest> implements I18nAware {

    private ComboBox<Long> vehicleId = new ComboBox<>(i18n("vehicle"));
    private ComboBox<RequestType> requestTypeComboBox = new ComboBox<>(i18n("requestType"));
    private TextArea description = new TextArea(i18n("description"));
    private DateTimeField registrationTime = new DateTimeField(i18n("registrationTime"));

    public ServiceEditor() {
        super(ServiceRequest.class);
    }


    @Override
    protected Component createContent() {

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));

        requestTypeComboBox.setItems(RequestType.values());

        return new MVerticalLayout(
                new MHorizontalLayout(
                        new MVerticalLayout(vehicleId, requestTypeComboBox, description, registrationTime),
                        new MVerticalLayout())
                        .withFullWidth(),
                getToolbar())
                .withStyleName("with-small-frame");
    }

    @Override
    public Window openInModalPopup() {
        Window w = super.openInModalPopup();
        w.setHeight("95%");
        w.setWidth("70%");
        return w;
    }

}
