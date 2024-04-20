package com.mobicom.repository;

import com.mobicom.model.User;
import com.mobicom.model.UserRole;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
