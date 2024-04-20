package com.mobicom.controller;

import com.mobicom.model.Payment;
import com.mobicom.model.PaymentStatus;
import com.mobicom.model.Plan;
import com.mobicom.model.RechargeHistory;
import com.mobicom.model.User;
import com.mobicom.security.Encryption;
import com.mobicom.service.PlanService;
import com.mobicom.service.RechargeHistoryService;
import com.mobicom.service.UserService;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/payment")
public class PaymentController {

  @Autowired
  private RechargeHistoryService rechargeHistoryService;

  @Autowired
  private UserService userService;

  @Autowired
  private PlanService planService;

  @Autowired
  private Encryption encryption;

  @GetMapping("/{encryptedId}")
  public RedirectView confirmPayment(@PathVariable String encryptedId) {
    try {
      Integer rechargeHistoryId = encryption.decrypt(encryptedId);
      RechargeHistory rechargeHistory = rechargeHistoryService.getRechargeHistoryById(
        rechargeHistoryId
      );
      if (rechargeHistory == null) {
        return new RedirectView("http://localhost:4200/payment/error");
      }

      Payment payment = rechargeHistory.getPayment();
      Calendar calendar = Calendar.getInstance();

      calendar.setTime(rechargeHistory.getRechargeDate());
      calendar.add(Calendar.MINUTE, 5);
      Date fiveMinutesAfter = calendar.getTime();

      if (new Date().after(fiveMinutesAfter)) {
        payment.setPaymentStatus(PaymentStatus.FAILURE);
        rechargeHistoryService.updateRechargeHistory(rechargeHistory);
        return new RedirectView("http://localhost:4200/payment/error");
      }

      User user = userService.getUserById(rechargeHistory.getUser().getId());
      Plan plan = planService.getPlanById(rechargeHistory.getPlan().getId());
      calendar.setTime(new Date());
      user.setLastRechargeDate(calendar.getTime());
      calendar.add(Calendar.DATE, plan.getValidity());
      user.setRechargeExpiryDate(calendar.getTime());
      userService.updateUser(user);

      payment.setPaymentStatus(PaymentStatus.SUCCESS);
      rechargeHistory.setRechargeDate(user.getLastRechargeDate());
      rechargeHistoryService.updateRechargeHistory(rechargeHistory);

      String successUrl = "http://localhost:4200/payment/success";
      return new RedirectView(successUrl);
    } catch (Exception e) {
      e.printStackTrace();
      return new RedirectView("http://localhost:4200/payment/error");
    }
  }
}
