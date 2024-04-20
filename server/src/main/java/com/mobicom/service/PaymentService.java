package com.mobicom.service;

import com.mobicom.model.Payment;
import com.mobicom.model.PaymentStatus;
import com.mobicom.model.RechargeHistory;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

  @Autowired
  private RechargeHistoryService rechargeHistoryService;

  @Scheduled(fixedRate = 60000)
  public void updatePaymentStatus() {
    try {
      List<RechargeHistory> pendingPayments = rechargeHistoryService.findPendingPayments();
      Date currentTime = new Date();

      for (RechargeHistory rechargeHistory : pendingPayments) {
        Date rechargeDate = rechargeHistory.getRechargeDate();
        if (currentTime.getTime() - rechargeDate.getTime() > 5 * 60 * 1000) {
          Payment payment = rechargeHistory.getPayment();
          payment.setPaymentStatus(PaymentStatus.FAILURE);
          rechargeHistoryService.updateRechargeHistory(rechargeHistory);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
