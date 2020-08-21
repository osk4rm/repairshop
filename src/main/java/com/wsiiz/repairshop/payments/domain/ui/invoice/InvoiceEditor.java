package com.wsiiz.repairshop.payments.domain.ui.invoice;

import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.wsiiz.repairshop.customers.domain.customer.Customer;
import com.wsiiz.repairshop.enterprise.domain.branch.Branch;
import com.wsiiz.repairshop.enterprise.domain.employee.Employee;
import com.wsiiz.repairshop.enterprise.domain.employee.EmployeeService;
import com.wsiiz.repairshop.enterprise.domain.employee.SkillType;
import com.wsiiz.repairshop.foundation.ui.BaseEditor;
import com.wsiiz.repairshop.payments.domain.invoice.Invoice;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceService;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceStatus;
import com.wsiiz.repairshop.servicing.domain.service.RequestType;
import com.wsiiz.repairshop.vehicles.domain.Vehicle;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


public class InvoiceEditor extends BaseEditor<Invoice> {

    InvoiceService invoiceService;

    ComboBox<Long> customerId = new ComboBox<>(i18n("customer"));

    TextField customerAddress = new TextField(i18n("customerAddress"));

    DateField invoiceDate = new DateField(i18n("invoiceDate"));

    public InvoiceEditor(InvoiceService invoiceService) {
        super(Invoice.class);
        this.invoiceService = invoiceService;
    }


    @Override
    protected Component createContent() {

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));

        customerId.setItems(invoiceService.getCustomers().stream().map(Customer::getId).collect(Collectors.toList()));
        customerId.setItemCaptionGenerator(e -> "ID: " + e + " | " + invoiceService.getCustomerName(e));

        getEntity().setStatus(InvoiceStatus.PREPARED);
        getEntity().setInvoiceDate(LocalDate.now());

        return new MVerticalLayout(
                new MHorizontalLayout(
                        new MVerticalLayout(customerId, customerAddress, invoiceDate),
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
