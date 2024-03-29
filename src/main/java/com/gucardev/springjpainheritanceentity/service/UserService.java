package com.gucardev.springjpainheritanceentity.service;

import com.gucardev.springjpainheritanceentity.model.User;
import com.gucardev.springjpainheritanceentity.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUser(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}