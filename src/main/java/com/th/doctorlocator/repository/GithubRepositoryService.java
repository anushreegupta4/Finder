package com.th.doctorlocator.repository;

import com.th.doctorlocator.config.github.GithubConfig;
import com.th.doctorlocator.model.Collaborators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GithubRepositoryService {

  private final GithubConfig githubConfig;
  private final GithubConnector githubConnector;

  @Autowired
  public GithubRepositoryService(GithubConfig githubConfig, GithubConnector githubConnector) {
    this.githubConfig = githubConfig;
    this.githubConnector = githubConnector;
  }

  public Collaborators getCollaborators(String organisation, String repository) {
    String url = String.format(githubConfig.getRepositoryContributorsUrl(), organisation, repository);
    return githubConnector.executeGet(url, Collaborators.class);
  }
}
