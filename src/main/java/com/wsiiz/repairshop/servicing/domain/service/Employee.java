package com.wsiiz.repairshop.servicing.domain.service;

import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Employee {

  Long employeeId;
  String employeeName;
}
