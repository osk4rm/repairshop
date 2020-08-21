package com.wsiiz.repairshop.payments.domain.invoice;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class InvoiceItems extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    Invoice invoice;

    String item;
    Integer price;

    public InvoiceItems(Invoice invoice, String item, Integer price) {
        this.invoice = invoice;
        this.item = item;
        this.price = price;
    }
}
