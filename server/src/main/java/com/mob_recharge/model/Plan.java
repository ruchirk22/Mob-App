package com.mob_recharge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PlanCategory category;

  private String description;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private Integer validity;

  private Integer data = 0;

  private Boolean isPerDay = false;

  private BigDecimal calls = BigDecimal.ZERO;

  private Integer sms = 0;

  @Column(nullable = false)
  private Boolean isDeleted = false;
}
