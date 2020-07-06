package com.wsiiz.repairshop.servicing.domain.service;

import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class Service extends BaseEntity {

  LocalDateTime registrationTime;

  Boolean requiresWashing;

  String description;

  @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  List<Task> tasks;

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
}
