package com.mobicom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MobicomApplication {

  public static void main(String[] args) {
    SpringApplication.run(MobicomApplication.class, args);
  }
}
