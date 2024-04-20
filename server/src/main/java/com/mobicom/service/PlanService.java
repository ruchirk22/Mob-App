package com.mobicom.service;

import com.mobicom.model.Plan;
import com.mobicom.model.PlanCategory;
import com.mobicom.repository.PlanRepository;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

  @Autowired
  private PlanRepository planRepository;

  public Plan savePlan(Plan plan) {
    return planRepository.save(plan);
  }

  public List<Plan> getAllPlans() {
    return planRepository.findByIsDeletedFalse();
  }

  public List<Plan> getPlansByCategory(String category) {
    try {
      PlanCategory planCategory = PlanCategory.valueOf(category.toUpperCase());
      return planRepository.findByCategoryAndIsDeletedFalse(planCategory);
    } catch (IllegalArgumentException e) {
      e.getMessage();
      return Collections.emptyList();
    }
  }

  public Plan getPlanById(Integer id) {
    return planRepository.findByIdAndIsDeletedFalse(id).orElse(null);
  }

  public List<Plan> deletePlan(Integer id) {
    Plan plan = planRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));

    plan.setIsDeleted(true);

    planRepository.save(plan);

    return getAllPlans();
  }

  public int getDaysLeftInCurrentPlan(Date expiryDate, int validityDays) {
    if (expiryDate == null) {
      return 0;
    }

    Calendar expiryDateCal = Calendar.getInstance();
    expiryDateCal.setTime(expiryDate);
    Calendar currentDate = Calendar.getInstance();

    long currentTimeInMillis = currentDate.getTimeInMillis();
    long planExpiryTimeInMillis = expiryDateCal.getTimeInMillis();
    long daysLeft =
      (planExpiryTimeInMillis - currentTimeInMillis) / (24 * 60 * 60 * 1000);

    return (int) daysLeft;
  }
}
