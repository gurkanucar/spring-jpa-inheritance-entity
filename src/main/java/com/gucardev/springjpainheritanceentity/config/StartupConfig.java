package com.gucardev.springjpainheritanceentity.config;

import com.gucardev.springjpainheritanceentity.model.User;
import com.gucardev.springjpainheritanceentity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {

  private final UserService userService;

  public StartupConfig(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void run(String... args) throws Exception {
    userService.saveUser(
        User.builder().username("grkn").displayName("Gurkan").email("grkn@mail.com").build());
    userService.saveUser(
        User.builder().username("ali").displayName("Ali").email("ali@mail.com").build());
    userService.saveUser(
        User.builder().username("metin").displayName("Metin").email("metin@mail.com").build());
  }
}
