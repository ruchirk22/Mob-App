package com.mobicom.service;

import com.mobicom.model.User;
import com.mobicom.model.UserRole;
import com.mobicom.repository.UserRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User saveUser(User user) {
    if (
      userRepository.existsByUsername(user.getUsername()) ||
      userRepository.existsByEmail(user.getEmail())
    ) {
      throw new RuntimeException("Username or email already exists!");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }

  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username).orElse(null);
  }

  public List<User> getAllSubscribers() {
    return userRepository.findByRole(UserRole.SUBSCRIBER);
  }

  public List<User> getAllSubscribersWithExpiryDate() {
    Date now = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(now);
    calendar.add(Calendar.DATE, 3);
    Date threeDaysFromNow = calendar.getTime();
    return userRepository.findByRoleAndRechargeExpiryDateBetween(
      UserRole.SUBSCRIBER,
      now,
      threeDaysFromNow
    );
  }

  public void deleteUser(Integer id) {
    userRepository.deleteById(id);
  }

  public User updateUser(User user) {
    User existingUser = getUserById(user.getId());
    if (existingUser != null) {
      return userRepository.save(user);
    } else {
      throw new IllegalArgumentException(
        "Recharge history with ID " + user.getId() + " does not exist."
      );
    }
  }
}
