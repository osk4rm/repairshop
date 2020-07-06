package com.wsiiz.repairshop.customers.domain.customer;

import com.wsiiz.repairshop.foundation.domain.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PersonService implements AbstractService<Person> {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  ApplicationEventPublisher applicationEventPublisher;

  @Override
  public Person save(Person entity) {

    entity = personRepository.save(entity);

    applicationEventPublisher.publishEvent(new CustomerChangedEvent(this, entity));

    return entity;
  }

}
