package com.wsiiz.repairshop.payments.domain.invoice;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

  Optional<Invoice> findByStatusAndCustomerId(InvoiceStatus status, Long customerId);
}
