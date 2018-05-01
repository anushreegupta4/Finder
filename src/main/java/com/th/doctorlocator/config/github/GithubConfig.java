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
  private String repositoryContributors;

  public String getRepositoryContributors() {
    return repositoryContributors;
  }

  public String getOrganisationReposUrl() {
    return organisationReposUrl;

  }
}
