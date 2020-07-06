package com.wsiiz.repairshop.enterprise.application;

import com.wsiiz.repairshop.enterprise.domain.branch.Branch;
import com.wsiiz.repairshop.enterprise.domain.branch.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BranchController {
    @Autowired
    BranchRepository branchRepository;

    @GetMapping("/branches")
    public ResponseEntity<List<Branch>> getMany(
            @RequestParam(value = "locality", required = false) String locality,
            @RequestParam(value = "parentBranchId", required = false) Long parentBranchId) {
        return ResponseEntity.ok(branchRepository.findByLocalityOrParent(locality, parentBranchId));
    }

    @GetMapping("/branches/{id}")
    public ResponseEntity<Branch> getOne(@PathVariable("id") Long id) {

        Optional<Branch> branch = branchRepository.findById(id);

        if (branch.isPresent()) {
            return ResponseEntity.ok(branch.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/branches")
    public ResponseEntity<Branch> addNew(@RequestBody Branch branch) {
        return ResponseEntity.created(null).body(branchRepository.save(branch));
    }

    @DeleteMapping("/branches/{id}")
    public ResponseEntity<Branch> remove(@PathVariable("id") Long id) {

        Optional<Branch> branch = branchRepository.findById(id);
        if (branchRepository.findByParentBranchId(id).isEmpty()) {
            if (branch.isPresent()) {
                branchRepository.deleteById(id);
                return ResponseEntity.ok(branch.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}

