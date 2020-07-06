package com.wsiiz.repairshop.security.domain.useraccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class UserRole extends BaseEntity {

  Long roleId;
  Boolean active;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_account_id")
  @JsonIgnore
  UserAccount userAccount;
}
