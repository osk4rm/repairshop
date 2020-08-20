package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.Employee;
import com.wsiiz.repairshop.servicing.domain.service.ServiceService;
import com.wsiiz.repairshop.servicing.domain.service.Status;
import com.wsiiz.repairshop.servicing.domain.service.Task;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import java.util.ArrayList;
import java.util.List;

public class TaskEditor extends AbstractForm<Task> implements I18nAware {

    ServiceService service;
    TextArea description = new TextArea(i18n("description"));
    ComboBox<Employee> responsiblePerson = new ComboBox<>(i18n("employee"));
    IntegerField price = new IntegerField(i18n("price"));

    public TaskEditor(ServiceService service) {
      super(Task.class);
      this.service = service;
    }

    @Override
    protected Component createContent() {

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));

        responsiblePerson.setItems(employeesList());

        return new MVerticalLayout(
                new MHorizontalLayout(
                        new MVerticalLayout(description),
                        new MVerticalLayout(responsiblePerson, price))
                        .withFullWidth(),
                getToolbar())
                .withStyleName("with-small-frame");
    }

    protected List<Employee> employeesList() {

        List<Employee> employees = new ArrayList<>();

        service.getEmployees().forEach(employee -> {
            employees.add(new Employee(employee.getId(),employee.toString()));
        });

        return employees;
    }

    @Override
    public Window openInModalPopup() {
        Window w = super.openInModalPopup();
        w.setHeight("95%");
        w.setWidth("70%");
        return w;
    }

}
