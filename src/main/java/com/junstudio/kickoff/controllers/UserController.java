package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
  private UserService userService;

  @GetMapping("me")
  public UserDto user() {
    User user = userService.findUser(1L);
    return user.toDto();
  }
}
