package com.wsiiz.repairshop.enterprise.domain.branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query(value = "select b from Branch b where (:locality = null or b.branchAddress.locality = :locality)"
            + "and (:parentBranchId = null or b.parentBranchId = :parentBranchId)")
    List<Branch> findByLocalityOrParent(
            @Param("locality") String locality,
            @Param("parentBranchId") Long id
    );

    @Query(value = "select b from Branch b where b.parentBranchId = :parentBranchId")
    List<Branch> findByParentBranchId(@Param("parentBranchId") Long parentBranchId);

    @Query(value = "select b from Branch b where b.name = :name")
    List<Branch> findByBranchName(@Param("name") Long name);

}
