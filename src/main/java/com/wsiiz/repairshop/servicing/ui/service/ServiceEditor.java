package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.ui.*;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.RequestType;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import com.wsiiz.repairshop.servicing.domain.service.ServiceService;
import com.wsiiz.repairshop.vehicles.domain.Vehicle;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ServiceEditor extends AbstractForm<Service> implements I18nAware {

    ServiceService service;

    private ComboBox<Long> vehicleId = new ComboBox<>(i18n("vehicle"));
    private ComboBox<RequestType> requestType = new ComboBox<>(i18n("requestType"));
    private TextArea description = new TextArea(i18n("description"));
    private DateTimeField registrationTime = new DateTimeField(i18n("registrationTime"));
    private CheckBox requiresWashing = new CheckBox(i18n("washing"));

    public ServiceEditor(ServiceService service) {
      super(Service.class);
      this.service = service;
    }

    @Override
    protected Component createContent() {

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));

        registrationTime.setDefaultValue(LocalDateTime.now());

        vehicleId.setItems(service.findVehicles().stream().map(Vehicle::getId).collect(Collectors.toList()));
        vehicleId.setItemCaptionGenerator(e -> service.getVehicleData(e));

        requestType.setItems(RequestType.values());
        requestType.setItemCaptionGenerator(e -> i18n(RequestType.class, e.name()));

        return new MVerticalLayout(
                new MHorizontalLayout(
                        new MVerticalLayout(vehicleId, requestType, description, registrationTime, requiresWashing),
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
