package com.wsiiz.repairshop.shared.domain;

import lombok.Data;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    String locality;
    String street;
    String apartmentNo;
    String postalCode;

}
