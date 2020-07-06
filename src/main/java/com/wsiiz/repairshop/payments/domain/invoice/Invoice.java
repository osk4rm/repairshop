package com.wsiiz.repairshop.payments.domain.invoice;

import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class Invoice extends BaseEntity {

  Long customerId;

  String customerAddress;

  @Enumerated(value = EnumType.STRING)
  InvoiceStatus status;
}
