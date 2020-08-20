package com.wsiiz.repairshop.payments.domain.ui.invoice;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.wsiiz.repairshop.enterprise.domain.employee.Employee;
import com.wsiiz.repairshop.enterprise.domain.employee.EmployeeFactory;
import com.wsiiz.repairshop.enterprise.domain.employee.EmployeeRepository;
import com.wsiiz.repairshop.enterprise.domain.employee.EmployeeService;
import com.wsiiz.repairshop.foundation.ui.BaseView;
import com.wsiiz.repairshop.payments.domain.invoice.Invoice;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceFactory;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceRepository;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.grid.MGrid;

@SpringComponent
@UIScope
@SpringView
public class InvoiceView extends BaseView<Invoice> {

    public InvoiceView(InvoiceFactory factory, InvoiceService service,
                       InvoiceRepository repository) {
        super(factory, service, repository, new InvoiceEditor(service));
    }


    @Override
    protected void addColumns(MGrid<Invoice> table) {

        table.addColumn(e -> e.getCustomerId()).setCaption("CUST_ID");
        table.addColumn(e -> e.getDueDate()).setCaption("DUE_DATE");
        table.addColumn(e -> e.getStatus()).setCaption("STATUS");

    }

}
