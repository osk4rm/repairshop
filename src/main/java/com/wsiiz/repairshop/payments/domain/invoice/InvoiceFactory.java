package com.wsiiz.repairshop.payments.domain.invoice;

import com.wsiiz.repairshop.foundation.domain.AbstractFactory;
import org.springframework.stereotype.Component;

@Component
public class InvoiceFactory implements AbstractFactory<Invoice> {

  @Override
  public Invoice create() {
    return new Invoice();
  }
}
