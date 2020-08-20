package com.wsiiz.repairshop.payments.domain.invoice;


import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class InvoiceItems {

    String item;
    Integer price;

    public InvoiceItems() {
    }

    public InvoiceItems(String item, Integer price) {
        this.item = item;
        this.price = price;
    }
}
