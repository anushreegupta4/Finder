package com.th.doctorlocator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
public class FinderConfiguration {

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}