package com.mobicom.repository;

import com.mobicom.model.PaymentStatus;
import com.mobicom.model.RechargeHistory;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeHistoryRepository
  extends JpaRepository<RechargeHistory, Integer> {
  public List<RechargeHistory> findByUserId(Integer userId);

  public List<RechargeHistory> findByPayment_PaymentStatus(
    PaymentStatus paymentStatus
  );

  public List<RechargeHistory> findByUserIdAndPayment_PaymentStatus(
    Integer userId,
    PaymentStatus paymentStatus
  );

  RechargeHistory findByRechargeDateAndUserId(
    Date rechargeDate,
    Integer userId
  );
}
