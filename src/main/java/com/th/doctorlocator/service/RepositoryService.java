package com.th.doctorlocator.service;

import com.th.doctorlocator.model.Collaborator;
import com.th.doctorlocator.repository.GithubRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RepositoryService {
  private GithubRepositoryService githubRepositoryService;

  @Autowired
  public RepositoryService(GithubRepositoryService githubRepositoryService) {
    this.githubRepositoryService = githubRepositoryService;
  }

  public List<Collaborator> getAllCollaborators(String org, String repo) {
    return githubRepositoryService.getCollaborators(org, repo).getCollaborators();
  }
}
