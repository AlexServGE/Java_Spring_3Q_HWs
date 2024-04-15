package org.example.IssueProvider;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class IssueProvider {

  private final WebClient webClient;

  public IssueProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
    webClient = WebClient.builder()
            .filter(loadBalancerExchangeFilterFunction)
            .build();
  }

  public List<IssueDto> getIssueById(long id) {
    String ipPortDnsIssueService = String.format("http://issue-service/issue/%d",id);
    ResponseEntity<List<IssueDto>> responseEntityIssueRequested = webClient.get()
            .uri(ipPortDnsIssueService)
            .retrieve()
            .toEntityList(IssueDto.class)
            .block();
    return Optional
            .ofNullable(responseEntityIssueRequested.getBody())
            .orElseThrow(()->new ErrorResponseException(responseEntityIssueRequested.getStatusCode()));

  }

  public IssueDto getIssuesByReaderId(long id) {
    String ipPortDnsIssueService = String.format("http://issue-service/issue?id=%d", id);
    IssueDto IssueRequested = webClient.get()
            .uri(ipPortDnsIssueService)
            .retrieve()
            .bodyToMono(IssueDto.class)
            .block();
    return IssueRequested;
  }
}
