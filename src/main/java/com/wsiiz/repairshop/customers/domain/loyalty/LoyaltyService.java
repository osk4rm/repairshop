package com.wsiiz.repairshop.customers.domain.loyalty;

import com.wsiiz.repairshop.customers.domain.customer.CustomerChangedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoyaltyService implements ApplicationListener<CustomerChangedEvent> {

  @Override
  public void onApplicationEvent(CustomerChangedEvent event) {
    log.info("Data of customer has changed: {}", event.getCustomer().toString());
  }

}
