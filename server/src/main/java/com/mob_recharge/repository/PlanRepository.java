package com.mob_recharge.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mob_recharge.model.Plan;
import com.mob_recharge.model.PlanCategory;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
  List<Plan> findByCategoryAndIsDeletedFalse(PlanCategory category);
  List<Plan> findByIsDeletedFalse();
  Optional<Plan> findByIdAndIsDeletedFalse(Integer id);
}
