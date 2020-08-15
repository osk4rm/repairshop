package com.wsiiz.repairshop.servicing.domain.service;

import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class Service extends BaseEntity {

  Long vehicleId;

  LocalDateTime registrationTime;

  Boolean requiresWashing;

  String description;

  @Enumerated(value = EnumType.STRING)
  RequestType requestType;

  @Enumerated(value = EnumType.STRING)
  Status status;

  @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  List<Task> tasks;

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
