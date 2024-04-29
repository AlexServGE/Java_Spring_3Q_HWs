package com.example.timerAOPstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimerAOPAutoConfiguration {

  @Bean
  TimerAspect getTimerAspect(){
    return new TimerAspect();
  }
}
