package com.wsiiz.repairshop.security.application;

import com.wsiiz.repairshop.security.domain.useraccount.UserAccount;
import com.wsiiz.repairshop.security.domain.useraccount.UserAccountRepository;
import java.util.List;
import java.util.Optional;

import com.wsiiz.repairshop.security.domain.useraccount.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {

  @Autowired
  UserAccountRepository userAccountRepository;

  @GetMapping("/user-accounts")
  public ResponseEntity<List<UserAccount>> getMany() {
    return ResponseEntity.ok(userAccountRepository.findAll());
  }

  @GetMapping("/user-accounts/{id}")
  public ResponseEntity<UserAccount> getOne(@PathVariable("id") Long id) {

    Optional<UserAccount> userAccount = userAccountRepository.findById(id);

    if (userAccount.isPresent()) {
      return ResponseEntity.ok(userAccount.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/user-accounts")
  public ResponseEntity<UserAccount> addNew(@RequestBody UserAccount userAccount) {
    if(userAccount.getRoles() != null) {
      for (UserRole r : userAccount.getRoles()) {
        r.setUserAccount(userAccount);
      }
    }
    return ResponseEntity.created(null).body(userAccountRepository.save(userAccount));
  }

  @DeleteMapping("/user-accounts/{id}")
  public ResponseEntity<UserAccount> remove(@PathVariable("id") Long id) {

    Optional<UserAccount> userAccount = userAccountRepository.findById(id);

    if (userAccount.isPresent()) {
      userAccountRepository.deleteById(id);
      return ResponseEntity.ok(userAccount.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
