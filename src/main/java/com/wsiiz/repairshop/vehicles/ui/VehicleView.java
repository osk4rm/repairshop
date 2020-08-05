package com.wsiiz.repairshop.vehicles.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.wsiiz.repairshop.customers.domain.customer.Person;
import com.wsiiz.repairshop.customers.domain.customer.PersonFactory;
import com.wsiiz.repairshop.customers.domain.customer.PersonRepository;
import com.wsiiz.repairshop.customers.domain.customer.PersonService;
import com.wsiiz.repairshop.customers.ui.customer.PersonEditor;
import com.wsiiz.repairshop.foundation.ui.BaseView;
import com.wsiiz.repairshop.vehicles.domain.Vehicle;
import com.wsiiz.repairshop.vehicles.domain.VehicleFactory;
import com.wsiiz.repairshop.vehicles.domain.VehicleRepository;
import com.wsiiz.repairshop.vehicles.domain.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.grid.MGrid;

@SpringComponent
@UIScope
@SpringView
public class VehicleView extends BaseView<Vehicle> {

  @Autowired
  VehicleService vehicleService;

  public VehicleView(VehicleFactory vehicleFactory, VehicleService vehicleService,
                     VehicleRepository vehicleRepository) {
    super(vehicleFactory, vehicleService, vehicleRepository, new VehicleEditor(vehicleService));
  }

  @Override
  protected void addColumns(MGrid<Vehicle> table) {

    table.addColumn(entity -> entity.getBrand() + " " + entity.getModel() + " " + entity.getEngineCapacity() + " CM3")
        .setCaption(i18n("vehicleDetails"));

    table.addColumn(entity -> entity.getFuelType())
            .setCaption(i18n("fuelType"));

    table.addColumn(entity -> entity.getPlateNumbers())
            .setCaption(i18n("PlateNumbers"));

    table.addColumn(entity -> entity.getOwnerId() + ": " + vehicleService.getCustomerName(entity.getOwnerId()))
            .setCaption(i18n("Owner"));
  }

}
