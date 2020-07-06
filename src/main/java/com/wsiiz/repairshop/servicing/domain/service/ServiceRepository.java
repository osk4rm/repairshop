package com.wsiiz.repairshop.servicing.domain.service;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Service, Long> {

  @Query(value = "select from Service where description = :description", nativeQuery = true)
  List<Service> findByDescription(@Param("description") String description);
}
