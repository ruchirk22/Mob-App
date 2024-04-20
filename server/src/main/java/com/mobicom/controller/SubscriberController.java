package com.mobicom.controller;

import com.mobicom.config.ResponseHandler;
import com.mobicom.model.Plan;
import com.mobicom.model.RechargeHistory;
import com.mobicom.model.User;
import com.mobicom.service.PlanService;
import com.mobicom.service.RechargeHistoryService;
import com.mobicom.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SubscriberController {

  @Autowired
  private RechargeHistoryService rechargeHistoryService;

  @Autowired
  private UserService userService;

  @Autowired
  private PlanService planService;

  @PostMapping("/recharges")
  public ResponseEntity<Object> createRechargeHistory(
    @RequestBody RechargeHistory rechargeHistory,
    Authentication authentication
  ) {
    try {
      rechargeHistoryService.createRechargeHistory(
        rechargeHistory,
        authentication
      );
      return new ResponseHandler(
        HttpStatus.CREATED,
        "Recharge in progress. Payment initiated!"
      );
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseHandler(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/recharges/my")
  public ResponseEntity<Object> getMyRechargeHistories(
    Authentication authentication
  ) {
    String username = authentication.getName();
    User user = userService.getUserByUsername(username);

    if (user != null) {
      Integer userId = user.getId();
      List<RechargeHistory> myRechargeHistories = rechargeHistoryService.getRechargeHistoriesByUserId(
        userId
      );
      return new ResponseHandler(HttpStatus.OK, myRechargeHistories);
    } else {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/recharges/summary")
  public ResponseEntity<Object> getRechargeSummary(
    Authentication authentication
  ) {
    String username = authentication.getName();
    User user = userService.getUserByUsername(username);

    if (user != null) {
      Integer userId = user.getId();
      int totalRecharges = rechargeHistoryService
        .getRechargeHistoriesByUserId(userId)
        .size();
      int pendingRecharges = rechargeHistoryService
        .findMyPendingPayments(userId)
        .size();
      int successfulRecharges = rechargeHistoryService
        .findMySuccessfulPayments(userId)
        .size();
      int failedRecharges = rechargeHistoryService
        .findMyFailedPayments(userId)
        .size();
      Plan currentPlan = rechargeHistoryService.getCurrentPlanByUser(
        userId,
        user.getLastRechargeDate()
      );
      int daysLeft = planService.getDaysLeftInCurrentPlan(
        user.getRechargeExpiryDate(),
        currentPlan.getValidity()
      );

      Map<String, Object> response = Map.of(
        "totalRecharges",
        totalRecharges,
        "pendingRecharges",
        pendingRecharges,
        "currentPlan",
        currentPlan,
        "daysLeft",
        daysLeft,
        "successfulRecharges",
        successfulRecharges,
        "failedRecharges",
        failedRecharges
      );
      return new ResponseHandler(HttpStatus.OK, response);
    } else {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
  }
}
