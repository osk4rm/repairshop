package com.wsiiz.repairshop.security.domain.useraccount;

import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class UserAccount extends BaseEntity {

  String name;
  String login;

  @Enumerated(value = EnumType.STRING)
  UserKind userKind;

  LocalDateTime registrationTime;
  LocalDateTime recentLoginTime;

  @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  List<UserRole> roles;
}
