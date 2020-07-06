package com.wsiiz.repairshop.enterprise.domain.branch;


import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import com.wsiiz.repairshop.shared.domain.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class Branch extends BaseEntity {

    String name;

    @AttributeOverrides({
            @AttributeOverride(name = "locality", column = @Column(name = "BRANCH_LOCALITY")),
            @AttributeOverride(name = "street", column = @Column(name = "BRANCH_STREET")),
            @AttributeOverride(name = "apartmentNo", column = @Column(name = "BRANCH_APARTMENT_NO")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "BRANCH_POSTAL_CODE"))
    })
    Address branchAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "locality", column = @Column(name = "CORRESPONDENCE_LOCALITY")),
            @AttributeOverride(name = "street", column = @Column(name = "CORRESPONDENCE_STREET")),
            @AttributeOverride(name = "apartmentNo", column = @Column(name = "CORRESPONDENCE_APARTMENT_NO")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "CORRESPONDENCE_POSTAL_CODE"))
    })
    Address correspondenceAddress;

    @Enumerated(value = EnumType.STRING)
    ActivityType activityType;

    Long parentBranchId;


}
