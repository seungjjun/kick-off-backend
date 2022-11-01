package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findUser(Long userId) {
    return userRepository.findById(userId).orElseThrow(UserNotFound::new);
  }
}
