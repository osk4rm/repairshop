package com.wsiiz.repairshop.payments.domain.invoice;

import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class Invoice extends BaseEntity {

  Long customerId;

  String customerAddress;

  LocalDate dueDate;

  @Enumerated(value = EnumType.STRING)
  InvoiceStatus status;

  Array[] items;

  public Invoice(Long customerId, LocalDate dueDate, InvoiceStatus status) {
    this.customerId = customerId;
    this.dueDate = dueDate;
    this.status = status;
  }
}
