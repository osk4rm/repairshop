package com.wsiiz.repairshop.payments.domain.invoice;

import com.wsiiz.repairshop.customers.domain.customer.Customer;
import com.wsiiz.repairshop.customers.domain.customer.CustomerChangedEvent;
import com.wsiiz.repairshop.foundation.domain.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InvoiceService implements AbstractService<Invoice>,
    ApplicationListener<CustomerChangedEvent> {

  @Autowired
  InvoiceRepository invoiceRepository;

  @Override
  public Invoice save(Invoice entity) {
    return invoiceRepository.save(entity);
  }

  @Override
  public void onApplicationEvent(CustomerChangedEvent event) {

    invoiceRepository.findByStatusAndCustomerId(InvoiceStatus.PREPARED, event.getCustomer().getId())
        .ifPresent(invoice -> {
          String newAddress = buildCustomerAddress(event.getCustomer());
          if (!invoice.getCustomerAddress().equals(newAddress)) {
            invoice.setCustomerAddress(newAddress);
            invoiceRepository.save(invoice);
          }
        });
  }

  private String buildCustomerAddress(Customer customer) {

    return customer.fullName()
        + (customer.getHomeAddress() == null ? "" :
        (customer.getHomeAddress().getStreet() + "\n"
            + customer.getHomeAddress().getPostalCode() + " "
            + customer.getHomeAddress().getLocality()));
  }

}
