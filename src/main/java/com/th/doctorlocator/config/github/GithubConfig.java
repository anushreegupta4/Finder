package com.th.doctorlocator.config.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class GithubConfig {
  @Value("${github.organisationrepos}")
  private String organisationReposUrl;

  @Value("${github.repocontributors}")
  private String repositoryContributorsUrl;

  @Value("${github.headerkey}")
  private String headerkey;

  @Value("${github.headervalue}")
  private String headervalue;

  public String getRepositoryContributorsUrl() {
    return repositoryContributorsUrl;
  }

  public String getOrganisationReposUrl() {
    return organisationReposUrl;

  }

  public String getHeaderkey() {
    return headerkey;
  }

  public String getHeadervalue() {
    return headervalue;
  }
}
