package com.wsiiz.repairshop.customers.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    @Query(value = "select c from Customer c where (:pesel = null or c.pesel = :pesel) "
            + "and (:name = null or c.name = :name) and (:surname = null or c.surname = :surname)")
    List<Person> findByCriteria(@Param("pesel") String pesel, @Param("name") String name,
                                  @Param("surname") String surname);
}
