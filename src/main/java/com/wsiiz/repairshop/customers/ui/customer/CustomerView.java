package com.wsiiz.repairshop.customers.ui.customer;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.wsiiz.repairshop.customers.domain.customer.Person;
import com.wsiiz.repairshop.customers.domain.customer.PersonFactory;
import com.wsiiz.repairshop.customers.domain.customer.PersonRepository;
import com.wsiiz.repairshop.customers.domain.customer.PersonService;
import com.wsiiz.repairshop.enterprise.ui.EmployeeEditor;
import com.wsiiz.repairshop.foundation.ui.BaseView;
import org.vaadin.viritin.grid.MGrid;

@SpringComponent
@UIScope
@SpringView
public class CustomerView extends BaseView<Person> {

  public CustomerView(PersonFactory personFactory, PersonService personService,
      PersonRepository personRepository) {
    super(personFactory, personService, personRepository, new PersonEditor());
  }

  @Override
  protected void addColumns(MGrid<Person> table) {

    table.addColumn(entity -> entity.getName() + " " + entity.getSurname())
        .setCaption(i18n("customer"));

    table.addColumn(entity -> entity.getPesel())
        .setCaption(i18n("identity"));

    table.addColumn(entity -> entity.getAddress())
        .setCaption(i18n("address"));
  }

}
