package com.wsiiz.repairshop.servicing.domain.servicerequest;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

  @Query(value = "select sr from ServiceRequest sr where (:vehicleId = null or sr.vehicleId = :vehicleId) "
      + "and (:requestType = null or sr.requestType = :requestType)")
  List<ServiceRequest> findBySelectionCriteria(@Param("vehicleId") Long vehicleId,
      @Param("requestType") RequestType requestType);
}
