package org.example.AspectIssueController;

import com.netflix.servo.Metric;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Aspect
@Slf4j
public class AspectIssueController {

  private final AtomicLong counterSuccessCalls;
  private final AtomicLong counterFailCalls;

  public AspectIssueController(MeterRegistry meterRegistry) {
    counterSuccessCalls = meterRegistry.gauge("issue_success_counter", new AtomicLong());
    counterFailCalls = meterRegistry.gauge("issue_fail_counter", new AtomicLong());
  }


  @Pointcut("@annotation(org.example.AspectIssueController.Count)")
  public void methodsForCountingCalls() {
  }

  @AfterReturning("methodsForCountingCalls()")
  public void methodCounterSuccessCalls(JoinPoint joinPoint) {
    log.info("Success {}",counterSuccessCalls.incrementAndGet());
  }

  @AfterThrowing(value = "methodsForCountingCalls()", throwing = "e")
  public void methodCounterFailCalls(Throwable e) {
    log.info("Failure {}",counterFailCalls.incrementAndGet());
  }
}
