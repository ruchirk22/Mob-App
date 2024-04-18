package com.mob_recharge.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mob_recharge.config.ResponseHandler;
import com.mob_recharge.dto.UserDto;
import com.mob_recharge.model.User;
import com.mob_recharge.service.AuthService;
import com.mob_recharge.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  // @Autowired
  // private UserDetailsService userDetailsService;

  @Autowired
  private AuthService authService;

  // @Autowired
  // private JwtHelper helper;

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody User user) {
    try {
      userService.saveUser(user);

      return new ResponseHandler(
        HttpStatus.CREATED,
        "User registered successfully!"
      );
    } catch (RuntimeException e) {
      e.printStackTrace();
      return new ResponseHandler(
        HttpStatus.BAD_REQUEST,
        "Username or email already exists!"
      );
    }
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(
    @RequestBody Map<String, String> request
  ) {
    String username = request.get("username");
    String password = request.get("password");
    authService.doAuthenticate(username, password);

    // UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    // String token = this.helper.generateToken(userDetails);
    String role = userService.getUserByUsername(username).getRole().name();

    Map<String, String> response = Map.of(
      // "token",
      // token,
      "username",
      username,
      "role",
      role
    );
    return new ResponseHandler(HttpStatus.OK, response);
  }

  @GetMapping("/me")
  public ResponseEntity<Object> getMe(Authentication authentication) {
    String username = authentication.getName();
    User user = userService.getUserByUsername(username);

    if (user != null) {
      UserDto userDTO = UserDto
        .builder()
        .id(user.getId())
        .username(user.getUsername())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .isActive(user.getIsActive())
        .lastRechargeDate(user.getLastRechargeDate())
        .rechargeExpiryDate(user.getRechargeExpiryDate())
        .role(user.getRole())
        .build();
      return ResponseEntity
        .ok()
        .body(new ResponseHandler(HttpStatus.OK, userDTO));
    } else {
      return new ResponseHandler(HttpStatus.NOT_FOUND);
    }
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<Object> exceptionHandler() {
    return new ResponseHandler(HttpStatus.UNAUTHORIZED, "Invalid credentials!");
  }
}
