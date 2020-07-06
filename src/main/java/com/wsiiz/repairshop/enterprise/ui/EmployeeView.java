package com.wsiiz.repairshop.enterprise.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.wsiiz.repairshop.enterprise.domain.employee.*;
import com.wsiiz.repairshop.foundation.ui.BaseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.grid.MGrid;

@SpringComponent
@UIScope
@SpringView
public class EmployeeView extends BaseView<Employee> {
    
    @Autowired
    EmployeeService employeeService;

    public EmployeeView(EmployeeFactory employeeFactory, EmployeeService employeeService,
                        EmployeeRepository employeeRepository) {
        super(employeeFactory, employeeService, employeeRepository, new EmployeeEditor(employeeService));
    }


    @Override
    protected void addColumns(MGrid<Employee> table) {

        table.addColumn(entity -> entity.getFirstName() + " " + entity.getLastName())
                .setCaption(i18n("employee"));

        table.addColumn(entity -> entity.getSkillType())
                .setCaption(i18n("employeeSkills"));

        table.addColumn(entity -> entity.getHireDate())
                .setCaption(i18n("employeeHireDate"));

        table.addColumn(entity -> employeeService.findBranchName(entity.getBranchId()))
                .setCaption(i18n("employeeBranchName"));
    }

}
