package com.wsiiz.repairshop.vehicles.ui;

import com.vaadin.data.Converter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.wsiiz.repairshop.customers.domain.customer.Customer;
import com.wsiiz.repairshop.enterprise.domain.branch.Branch;
import com.wsiiz.repairshop.enterprise.domain.employee.Employee;
import com.wsiiz.repairshop.enterprise.domain.employee.EmployeeService;
import com.wsiiz.repairshop.enterprise.domain.employee.SkillType;
import com.wsiiz.repairshop.foundation.ui.BaseEditor;
import com.wsiiz.repairshop.vehicles.domain.Brand;
import com.wsiiz.repairshop.vehicles.domain.FuelType;
import com.wsiiz.repairshop.vehicles.domain.Vehicle;
import com.wsiiz.repairshop.vehicles.domain.VehicleService;
import org.vaadin.viritin.fields.IntegerField;

import java.util.stream.Collectors;


public class VehicleEditor extends BaseEditor<Vehicle> {

    VehicleService vehicleService;

    VerticalLayout container;
    ComboBox<Brand> brand;
    TextField model;
    ComboBox<FuelType> fuelType;
    TextField engineCapacity;
    TextField plateNumbers;
    ComboBox<Long> ownerId;


    public VehicleEditor(VehicleService vehicleService) {
        super(Vehicle.class);
        this.vehicleService = vehicleService;
    }


    @Override
    protected Component createContent() {

        Design.read("VehicleEditor.html", this);

        fuelType.setItems(FuelType.values());
        ownerId.setItems(vehicleService.getCustomersList().stream().map(Customer::getId).collect(Collectors.toList()));

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));
        setCaptions();
        container.addComponent(getToolbar());
        return getCompositionRoot();
    }

    @Override
    public Window openInModalPopup() {
        Window w = super.openInModalPopup();
        w.setHeight("95%");
        w.setWidth("70%");
        return w;
    }
}
