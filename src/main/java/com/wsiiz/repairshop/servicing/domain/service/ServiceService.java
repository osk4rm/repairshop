package com.wsiiz.repairshop.servicing.domain.service;

import com.wsiiz.repairshop.enterprise.domain.employee.Employee;
import com.wsiiz.repairshop.enterprise.domain.employee.EmployeeRepository;
import com.wsiiz.repairshop.foundation.domain.AbstractService;
import com.wsiiz.repairshop.payments.domain.invoice.Invoice;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceItems;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceStatus;
import com.wsiiz.repairshop.vehicles.domain.Vehicle;
import com.wsiiz.repairshop.vehicles.domain.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceService implements AbstractService<Service> {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Service save(Service entity) {
        return serviceRepository.save(entity);
    }

    public List<Vehicle> findVehicles() {
        return vehicleRepository.findAll();
    }

    public String getVehicleData(Long id) {
        return vehicleRepository.findById(id).map(Vehicle::toString).orElse("");
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByServiceId(Service s) {
        return taskRepository.findAll().stream().filter(f -> f.getService().getId().equals(s.getId())).collect(Collectors.toList());
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public String getEmployeeData(Long id) {
        return employeeRepository.findById(id).map(Employee::toString).orElse("");
    }

    public Invoice invoiceGenerator(Service s) {

        Long customerId = vehicleRepository.findById(s.getVehicleId()).map(Vehicle::getOwnerId).orElse(Long.valueOf(0));
        String customerAddress = ""; //todo
        Invoice invoice = new Invoice(customerId,customerAddress,LocalDate.now(), InvoiceStatus.PREPARED);

        return invoice;
    }

    public List<InvoiceItems> generateInvoiceItems(Service s, Invoice invoice) {
        List<InvoiceItems> items = new ArrayList<>();

        getTasksByServiceId(s).forEach(e -> items.add(new InvoiceItems(invoice, e.getDescription(), e.getPrice())));

        return items;
    }


}
