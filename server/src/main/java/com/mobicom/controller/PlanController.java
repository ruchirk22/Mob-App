package com.mobicom.controller;

import com.mobicom.config.ResponseHandler;
import com.mobicom.model.Plan;
import com.mobicom.service.PlanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plans")
public class PlanController {

  @Autowired
  private PlanService planService;

  @GetMapping
  public ResponseEntity<Object> getAllPlans() {
    List<Plan> plans = planService.getAllPlans();
    if (plans.isEmpty()) {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
    return new ResponseHandler(HttpStatus.OK, plans);
  }

  @GetMapping("/category/{category}")
  public ResponseEntity<Object> getPlansByCategory(
    @PathVariable String category
  ) {
    List<Plan> plans = planService.getPlansByCategory(category);
    if (plans.isEmpty()) {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
    return new ResponseHandler(HttpStatus.OK, plans);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getPlanById(@PathVariable Integer id) {
    Plan plan = planService.getPlanById(id);
    if (plan != null) {
      return new ResponseHandler(HttpStatus.OK, plan);
    } else {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<Object> addPlan(@RequestBody Plan plan) {
    try {
      planService.savePlan(plan);
      return new ResponseHandler(
        HttpStatus.CREATED,
        "Plan added successfully!"
      );
    } catch (RuntimeException e) {
      e.printStackTrace();
      return new ResponseHandler(
        HttpStatus.BAD_REQUEST,
        "Plan already exists!"
      );
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deletePlanById(@PathVariable Integer id) {
    try {
      List<Plan> plans = planService.deletePlan(id);
      return new ResponseHandler(HttpStatus.OK, plans);
    } catch (RuntimeException e) {
      e.printStackTrace();
      return new ResponseHandler(HttpStatus.NOT_FOUND, "Plan not found!");
    }
  }
}
