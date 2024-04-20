package com.mobicom.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentType paymentType;

  @Column(nullable = false)
  private String paymentDetails;

  @Column(nullable = false)
  private String transactionId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentStatus paymentStatus = PaymentStatus.PENDING;
}
