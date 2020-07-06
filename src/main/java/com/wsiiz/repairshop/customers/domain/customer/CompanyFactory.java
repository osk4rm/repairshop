package com.wsiiz.repairshop.customers.domain.customer;

import org.springframework.stereotype.Component;

@Component
public class CompanyFactory {

    public Company create() {
        return new Company();
    }

}
