package com.mob_recharge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class mob_recharge{

  public static void main(String[] args) {
    SpringApplication.run(mob_recharge.class, args);
  }
}
