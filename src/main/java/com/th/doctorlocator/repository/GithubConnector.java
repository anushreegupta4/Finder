package com.th.doctorlocator.repository;

import com.th.doctorlocator.config.github.GithubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubConnector {

  private final GithubConfig githubConfig;
  private final RestTemplate myClient;

  @Autowired
  public GithubConnector(GithubConfig githubConfig, RestTemplate myClient) {
    this.githubConfig = githubConfig;
    this.myClient = myClient;
  }

  public HttpHeaders generateRequestHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(githubConfig.getHeaderkey(), githubConfig.getHeadervalue());
    return headers;
  }

  public <R> R executeGet(String url, Class<R> responseType) {
    ResponseEntity<R> responseEntity = this.get(url, responseType);
    return responseEntity.getBody();
  }

  private <R> ResponseEntity<R> get(String url, Class<R> clazz) {
    return myClient.exchange(url, HttpMethod.GET, new HttpEntity<Object>(generateRequestHeaders()), clazz);
  }

}
