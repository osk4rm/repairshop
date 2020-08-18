package com.wsiiz.repairshop.servicing.domain.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@ToString(exclude = {"service"})
public class Task extends BaseEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "service_id")
  @JsonIgnore
  Service service;

  String description;

  Employee responsiblePerson;

  @Enumerated(value = EnumType.STRING)
  Status status;

  public Task(Service service, String description) {
    this.service = service;
    this.description = description;
  }
}
