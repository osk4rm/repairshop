package com.wsiiz.repairshop.customers.domain.customer;

import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import java.time.LocalDate;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)                                                                   //wszystkie atrybuty znajda sie w pojedynczej tabeli, TABLE_PER_CLASS - zostana utworzone osobne tabele dla klas dziedziczacych
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)                                       //dodaje kolumne, ktora rozroznia typ (customer albo person)
public abstract class Customer extends BaseEntity {


  @AttributeOverrides({
      @AttributeOverride(name = "locality", column = @Column(name = "HOME_LOCALITY")),
      @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
      @AttributeOverride(name = "apartmentNo", column = @Column(name = "HOME_APARTMENT_NO")),
      @AttributeOverride(name = "postalCode", column = @Column(name = "HOME_POSTAL_CODE"))
  })
  Address homeAddress;

  @AttributeOverrides({
      @AttributeOverride(name = "locality", column = @Column(name = "CORRESPONDENCE_LOCALITY")),
      @AttributeOverride(name = "street", column = @Column(name = "CORRESPONDENCE_STREET")),
      @AttributeOverride(name = "apartmentNo", column = @Column(name = "CORRESPONDENCE_APARTMENT_NO")),
      @AttributeOverride(name = "postalCode", column = @Column(name = "CORRESPONDENCE_POSTAL_CODE"))
  })
  Address correspondenceAddress;

  public abstract String fullName();
}
