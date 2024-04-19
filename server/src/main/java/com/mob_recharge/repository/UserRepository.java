package com.mob_recharge.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mob_recharge.model.User;
import com.mob_recharge.model.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  public Optional<User> findByUsername(String username);

  public List<User> findByRole(UserRole role);

  public List<User> findByRoleAndRechargeExpiryDateBetween(
    UserRole role,
    Date startDate,
    Date endDate
  );

  public Boolean existsByUsername(String username);

  public Boolean existsByEmail(String username);
}
