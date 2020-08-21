package com.wsiiz.repairshop.payments.domain.invoice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItems, Long> {

}
