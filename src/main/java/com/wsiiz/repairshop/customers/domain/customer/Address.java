package com.wsiiz.repairshop.customers.domain.customer;

import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {

  String locality;
  String street;
  String apartmentNo;
  String postalCode;
}
