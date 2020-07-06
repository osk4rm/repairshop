package com.wsiiz.repairshop.enterprise.domain.employee;

import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class
Employee extends BaseEntity {

    String firstName;
    String lastName;
    SkillType skillType;
    LocalDate hireDate;
    Long branchId;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<EmployeeSkill> employeeSkills;

}
