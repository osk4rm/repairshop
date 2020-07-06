package com.wsiiz.repairshop.enterprise.domain.employee;

import com.wsiiz.repairshop.foundation.domain.AbstractFactory;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFactory implements AbstractFactory<Employee> {
    
    public Employee create(){
        return new Employee();
    }
    
}
