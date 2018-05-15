package com.th.doctorlocator.service;

import com.th.doctorlocator.model.Repo;
import com.th.doctorlocator.repository.GithubOrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService {

  private final GithubOrganisationService githubOrganisationService;

  @Autowired
  public OrganisationService(GithubOrganisationService githubOrganisationService) {
    this.githubOrganisationService = githubOrganisationService;
  }

  public List<Repo> getAllReposForOrganisation(String organisation) {
    return githubOrganisationService.getReposForOrganisation(organisation).getRepos();
  }
}
