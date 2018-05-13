package com.th.doctorlocator.repository;

import com.th.doctorlocator.config.github.GithubConfig;
import com.th.doctorlocator.model.Repos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GithubOrganisationService {

  private final GithubConfig githubConfig;
  private final GithubConnector githubConnector;

  @Autowired
  public GithubOrganisationService(GithubConfig githubConfig, GithubConnector githubConnector) {
    this.githubConfig = githubConfig;
    this.githubConnector = githubConnector;
  }

  public Repos getReposForOrganisation(String organisation) {
    String url = String.format(githubConfig.getOrganisationReposUrl(), organisation);
    return githubConnector.executeGet(url, Repos.class);
  }
}
