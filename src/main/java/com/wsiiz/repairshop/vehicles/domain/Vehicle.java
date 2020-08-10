package com.wsiiz.repairshop.vehicles.domain;


import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class Vehicle extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    Brand brand;

    String model;

    @Enumerated(value = EnumType.STRING)
    FuelType fuelType;

    String engineCapacity;

    String plateNumbers;

    Long ownerId;

    @Override
    public String toString() {
        return id + " | " + brand + " " + model + " | " + "PLATE NO: " + plateNumbers;
    }
}
