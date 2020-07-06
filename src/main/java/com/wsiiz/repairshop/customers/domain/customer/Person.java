package com.wsiiz.repairshop.customers.domain.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@DiscriminatorValue("PERSON")
public class Person extends Customer {

    String name;
    String surname;
    LocalDate birthDate;
    String pesel;

    @Enumerated(value = EnumType.STRING)
    Sex sex;

  @Override
  public String fullName() {
    return name + " " + surname;
  }
}
