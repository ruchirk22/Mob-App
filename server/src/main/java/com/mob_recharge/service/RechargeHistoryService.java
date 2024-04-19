package com.mob_recharge.service;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mob_recharge.email.EmailService;
import com.mob_recharge.model.PaymentStatus;
import com.mob_recharge.model.Plan;
import com.mob_recharge.model.RechargeHistory;
import com.mob_recharge.model.User;
import com.mob_recharge.repository.RechargeHistoryRepository;

@Service
public class RechargeHistoryService {

  @Autowired
  private RechargeHistoryRepository rechargeHistoryRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private PlanService planService;

  @Autowired
  private EmailService emailService;


  public RechargeHistory createRechargeHistory(
    RechargeHistory rechargeHistory,
    Authentication authentication
  ) {
    try {
      RechargeHistory newRechargeHistory = saveRechargeHistory(rechargeHistory);

      Resource resource = new ClassPathResource(
        "emailTemplates/RechargeConfirmation.html"
      );
      File file = resource.getFile();
      User user = userService.getUserById(newRechargeHistory.getUser().getId());
      Plan plan = planService.getPlanById(newRechargeHistory.getPlan().getId());
      if (user != null && plan != null) {
        String htmlContent = new String(Files.readAllBytes(file.toPath()));
        htmlContent = htmlContent.replace("{{username}}", user.getUsername());
        htmlContent = htmlContent.replace("{{planName}}", plan.getName());
        htmlContent =
          htmlContent.replace(
            "{{planPrice}}",
            String.valueOf(plan.getAmount())
          );

        htmlContent =
          htmlContent.replace(
            "{{confirmationLink}}",
            "http://localhost:8082/payment/" +newRechargeHistory.getId() 
          );

        emailService.sendEmailWithHtml(
          user.getEmail(),
          "Confirmation of Payment for Prepaid Mobile Recharge",
          htmlContent
        );
        newRechargeHistory.setEmailSentStatus(true);
        return updateRechargeHistory(newRechargeHistory);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public RechargeHistory saveRechargeHistory(RechargeHistory rechargeHistory) {
    return rechargeHistoryRepository.save(rechargeHistory);
  }

  public List<RechargeHistory> getAllRechargeHistories() {
    return rechargeHistoryRepository.findAll();
  }

  public RechargeHistory getRechargeHistoryById(Integer id) {
    return rechargeHistoryRepository.findById(id).orElse(null);
  }

  public List<RechargeHistory> getRechargeHistoriesByUserId(Integer userId) {
    return rechargeHistoryRepository.findByUserId(userId);
  }

  public void deleteRechargeHistory(Integer id) {
    rechargeHistoryRepository.deleteById(id);
  }

  public RechargeHistory updateRechargeHistory(
    RechargeHistory rechargeHistory
  ) {
    RechargeHistory existingRechargeHistory = getRechargeHistoryById(
      rechargeHistory.getId()
    );
    if (existingRechargeHistory != null) {
      return rechargeHistoryRepository.save(rechargeHistory);
    } else {
      throw new IllegalArgumentException(
        "Recharge history with ID " +
        rechargeHistory.getId() +
        " does not exist."
      );
    }
  }

  public List<RechargeHistory> findPendingPayments() {
    return rechargeHistoryRepository.findByPayment_PaymentStatus(
      PaymentStatus.PENDING
    );
  }

  public List<RechargeHistory> findMyPendingPayments(int userId) {
    return rechargeHistoryRepository.findByUserIdAndPayment_PaymentStatus(
      userId,
      PaymentStatus.PENDING
    );
  }

  public List<RechargeHistory> findMySuccessfulPayments(int userId) {
    return rechargeHistoryRepository.findByUserIdAndPayment_PaymentStatus(
      userId,
      PaymentStatus.SUCCESS
    );
  }

  public List<RechargeHistory> findMyFailedPayments(int userId) {
    return rechargeHistoryRepository.findByUserIdAndPayment_PaymentStatus(
      userId,
      PaymentStatus.FAILURE
    );
  }

  public Plan getCurrentPlanByUser(int userId, Date lastRechargeDate) {
    if (lastRechargeDate != null) {
      RechargeHistory rechargeHistory = findByRechargeDateAndUserId(
        lastRechargeDate,
        userId
      );

      if (rechargeHistory != null) {
        return rechargeHistory.getPlan();
      }
    }

    return null;
  }

  public RechargeHistory findByRechargeDateAndUserId(
    Date rechargeDate,
    Integer userId
  ) {
    return rechargeHistoryRepository.findByRechargeDateAndUserId(
      rechargeDate,
      userId
    );
  }
}
