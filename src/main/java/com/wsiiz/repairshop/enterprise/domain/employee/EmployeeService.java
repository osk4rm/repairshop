package com.wsiiz.repairshop.enterprise.domain.employee;

import com.wsiiz.repairshop.enterprise.domain.branch.Branch;
import com.wsiiz.repairshop.enterprise.domain.branch.BranchRepository;
import com.wsiiz.repairshop.foundation.domain.AbstractService;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeService implements AbstractService<Employee>, I18nAware {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    BranchRepository branchRepository;


    @Override
    public Employee save(Employee entity) {

        return employeeRepository.save(entity);
    }
    public List<Branch> findBranches(){
        return branchRepository.findAll();
       }

    public String findBranchName(Long id) {
        return branchRepository.findById(id).map(Branch::getName).orElse("");
    }

}