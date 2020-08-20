package com.wsiiz.repairshop.payments.domain.ui.invoice;

import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.wsiiz.repairshop.enterprise.domain.branch.Branch;
import com.wsiiz.repairshop.enterprise.domain.employee.Employee;
import com.wsiiz.repairshop.enterprise.domain.employee.EmployeeService;
import com.wsiiz.repairshop.enterprise.domain.employee.SkillType;
import com.wsiiz.repairshop.foundation.ui.BaseEditor;
import com.wsiiz.repairshop.payments.domain.invoice.Invoice;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceService;
import com.wsiiz.repairshop.servicing.domain.service.RequestType;
import com.wsiiz.repairshop.vehicles.domain.Vehicle;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


public class InvoiceEditor extends BaseEditor<Invoice> {

    InvoiceService invoiceService;

    public InvoiceEditor(InvoiceService invoiceService) {
        super(Invoice.class);
        this.invoiceService = invoiceService;
    }


    @Override
    protected Component createContent() {

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));


        return new MVerticalLayout(
                new MHorizontalLayout(
                        new MVerticalLayout(),
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
