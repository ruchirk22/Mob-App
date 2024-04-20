package com.mobicom.dto;

import com.mobicom.model.UserRole;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

  private Integer id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private Boolean isActive;
  private Date lastRechargeDate;
  private Date rechargeExpiryDate;
  private UserRole role;
}
