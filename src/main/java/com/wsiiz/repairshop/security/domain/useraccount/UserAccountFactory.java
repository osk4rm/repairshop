package com.wsiiz.repairshop.security.domain.useraccount;

import org.springframework.stereotype.Component;

@Component
public class UserAccountFactory {

  public UserAccount create() {
    return new UserAccount();
  }
}
