package com.wsiiz.repairshop.customers.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "select c from Company c where :name = null or c.name = :name")
    List<Company> findByCriteria(@Param("name") String name);

}
