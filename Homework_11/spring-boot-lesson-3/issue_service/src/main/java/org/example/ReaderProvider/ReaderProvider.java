package org.example.ReaderProvider;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ReaderProvider {

  private final WebClient webClient;

  public ReaderProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
    webClient = WebClient.builder()
//            .filter(loadBalancerExchangeFilterFunction)
            .build();
  }
  public ReaderDto getReaderById(long id) {
    String ipPortDnsReaderService = String.format("http://localhost:8280/reader/%d",id);
    ReaderDto readerRequested = webClient.get()
            .uri(ipPortDnsReaderService)
            .retrieve()
            .bodyToMono(ReaderDto.class)
            .block();
    return readerRequested;
  }
}
