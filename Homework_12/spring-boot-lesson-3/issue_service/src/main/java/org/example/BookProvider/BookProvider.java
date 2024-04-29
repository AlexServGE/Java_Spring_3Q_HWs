package org.example.BookProvider;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BookProvider {

  private final WebClient webClient;

  public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
    webClient = WebClient.builder()
//            .filter(loadBalancerExchangeFilterFunction)
            .build();
  }
  public BookDto getBookById(long id) {
    String ipPortDnsBookService = String.format("http://localhost:8080/book/%d",id);
    BookDto bookRequested = webClient.get()
            .uri(ipPortDnsBookService)
            .retrieve()
            .bodyToMono(BookDto.class)
            .block();
    return bookRequested;
  }
}
