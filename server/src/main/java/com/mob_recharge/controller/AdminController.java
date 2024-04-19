package com.mob_recharge.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mob_recharge.config.ResponseHandler;
import com.mob_recharge.model.RechargeHistory;
import com.mob_recharge.model.User;
import com.mob_recharge.service.RechargeHistoryService;
import com.mob_recharge.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private UserService userService;

  @Autowired
  private RechargeHistoryService rechargeHistoryService;

  @GetMapping("/users")
  public ResponseEntity<Object> getAllSubscribers() {
    List<User> subscribers = userService.getAllSubscribers();
    if (subscribers.isEmpty()) {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
    return new ResponseHandler(HttpStatus.OK, subscribers);
  }

  @GetMapping("/users/expiring")
  public ResponseEntity<Object> getAllSubscribersWithExpiryDate() {
    List<User> subscribers = userService.getAllSubscribersWithExpiryDate();
    if (subscribers.isEmpty()) {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
    return new ResponseHandler(HttpStatus.OK, subscribers);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<Object> getUser(@PathVariable Integer id) {
    User user = userService.getUserById(id);
    if (user == null) {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
    List<RechargeHistory> userRechargeHistories = rechargeHistoryService.getRechargeHistoriesByUserId(
      id
    );
    Map<String, Object> response = Map.of(
      "user",
      user,
      "recharges",
      userRechargeHistories
    );
    return new ResponseHandler(HttpStatus.OK, response);
  }
}
