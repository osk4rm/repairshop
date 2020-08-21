package com.wsiiz.repairshop.payments.domain.invoice;

import com.wsiiz.repairshop.foundation.domain.BaseEntity;

import javax.persistence.*;

import com.wsiiz.repairshop.servicing.domain.service.Task;
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

  LocalDate invoiceDate;

  @Enumerated(value = EnumType.STRING)
  InvoiceStatus status;

  @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  List<InvoiceItems> items;

  public Invoice(Long customerId, String customerAddress, LocalDate invoiceDate, InvoiceStatus status) {
    this.customerId = customerId;
    this.customerAddress = customerAddress;
    this.invoiceDate = invoiceDate;
    this.status = status;
  }


}
