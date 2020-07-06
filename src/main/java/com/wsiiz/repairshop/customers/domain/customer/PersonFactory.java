package com.wsiiz.repairshop.customers.domain.customer;

import com.wsiiz.repairshop.foundation.domain.AbstractFactory;
import org.springframework.stereotype.Component;

@Component
public class PersonFactory implements AbstractFactory<Person> {

  public Person create() {
    return new Person();
  }

}
