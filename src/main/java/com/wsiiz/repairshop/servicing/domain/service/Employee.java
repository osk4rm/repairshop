package com.wsiiz.repairshop.servicing.domain.service;

import javax.persistence.Embeddable;
import lombok.Data;


@Embeddable
@Data
public class Employee {

  Long employeeId;
  String employeeName;

  public Employee() {
  }

  public Employee(Long employeeId, String employeeName) {
    this.employeeId = employeeId;
    this.employeeName = employeeName;
  }



  @Override
  public String toString() {
    return employeeId + ": " + employeeName;
  }
}
