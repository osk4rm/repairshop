package com.wsiiz.repairshop.enterprise.ui;

import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.wsiiz.repairshop.enterprise.domain.branch.Branch;
import com.wsiiz.repairshop.enterprise.domain.employee.Employee;
import com.wsiiz.repairshop.enterprise.domain.employee.EmployeeService;
import com.wsiiz.repairshop.enterprise.domain.employee.SkillType;
import com.wsiiz.repairshop.foundation.ui.BaseEditor;

import java.util.stream.Collectors;


public class EmployeeEditor extends BaseEditor<Employee> {

    EmployeeService employeeService;

    VerticalLayout container;
    TextField firstName;
    TextField lastName;
    ComboBox<SkillType> skillType;
    DateField hireDate;
    ComboBox<Long> branchId;

    public EmployeeEditor(EmployeeService employeeService) {
        super(Employee.class);
        this.employeeService = employeeService;
    }


    @Override
    protected Component createContent() {

        Design.read("EmployeeEditor.html", this);

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));

        skillType.setItems(SkillType.values());
        skillType.setItemCaptionGenerator(e -> i18n(SkillType.class, e.name()));

        branchId.setItems(employeeService.findBranches().stream().map(Branch::getId).collect(Collectors.toList()));

        branchId.setItemCaptionGenerator(e -> employeeService.findBranchName(e));

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
