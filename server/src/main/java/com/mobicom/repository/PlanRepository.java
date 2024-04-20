package com.mobicom.repository;

import com.mobicom.model.Plan;
import com.mobicom.model.PlanCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
  List<Plan> findByCategoryAndIsDeletedFalse(PlanCategory category);
  List<Plan> findByIsDeletedFalse();
  Optional<Plan> findByIdAndIsDeletedFalse(Integer id);
}
