package com.wsiiz.repairshop.servicing.application;

import com.wsiiz.repairshop.servicing.domain.servicerequest.RequestType;
import com.wsiiz.repairshop.servicing.domain.servicerequest.ServiceRequest;
import com.wsiiz.repairshop.servicing.domain.servicerequest.ServiceRequestRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRequestController {

  @Autowired
  ServiceRequestRepository serviceRequestRepository;

  @GetMapping("/service-requests")
  public ResponseEntity<List<ServiceRequest>> getMany(
      @RequestParam(value = "vehicleId", required = false) Long vehicleId,
      @RequestParam(value = "requestType", required = false) RequestType requestType) {
    return ResponseEntity
        .ok(serviceRequestRepository.findBySelectionCriteria(vehicleId, requestType));
  }

  @GetMapping("/service-requests/{id}")
  public ResponseEntity<ServiceRequest> getOne(@PathVariable("id") Long id) {

    Optional<ServiceRequest> serviceRequest = serviceRequestRepository.findById(id);

    if (serviceRequest.isPresent()) {
      return ResponseEntity.ok(serviceRequest.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/service-requests")
  public ResponseEntity<ServiceRequest> addNew(@RequestBody ServiceRequest serviceRequest) {
    return ResponseEntity.created(null).body(serviceRequestRepository.save(serviceRequest));
  }

  @DeleteMapping("/service-requests/{id}")
  public ResponseEntity<ServiceRequest> remove(@PathVariable("id") Long id) {

    Optional<ServiceRequest> serviceRequest = serviceRequestRepository.findById(id);

    if (serviceRequest.isPresent()) {
      serviceRequestRepository.deleteById(id);
      return ResponseEntity.ok(serviceRequest.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
