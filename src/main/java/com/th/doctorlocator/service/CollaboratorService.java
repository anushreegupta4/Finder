package com.th.doctorlocator.service;

import com.th.doctorlocator.model.Collaborator;
import com.th.doctorlocator.model.Repo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CollaboratorService {

  private final OrganisationService organisationService;
  private final RepositoryService repositoryService;
  private ExecutorService executorService = Executors.newFixedThreadPool(10);
  ;

  @Autowired
  public CollaboratorService(OrganisationService organisationService, RepositoryService repositoryService) {
    this.organisationService = organisationService;
    this.repositoryService = repositoryService;
  }

  public List<Collaborator> getTopTenCollaboratorsForOrganisation(final String organisation) {

    List<Repo> reposForOrganisation = organisationService.getAllReposForOrganisation(organisation);
    List<CompletableFuture<List<Collaborator>>> listOfCollaboratorsFutures =
        reposForOrganisation.stream().map(repo ->
            CompletableFuture.supplyAsync(() -> {
              List<Collaborator> allCollaborators = repositoryService.getAllCollaborators(organisation, repo.name);
              return getTopTenCollaboratorsForRepo(allCollaborators);
            }, executorService))
            .collect(toList());

    CompletableFuture[] arrayOfCompletableFuture = new CompletableFuture[listOfCollaboratorsFutures.size()];
    arrayOfCompletableFuture = listOfCollaboratorsFutures.toArray(arrayOfCompletableFuture);
    CompletableFuture<Void> allFinished = CompletableFuture.allOf(arrayOfCompletableFuture);

    CompletableFuture<List<Collaborator>> listCompletableFuture = allFinished
        .thenApplyAsync(
            v -> {
              List<List<Collaborator>> intermediateList = listOfCollaboratorsFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
              return getTopTenCollaborators(intermediateList);
            }, executorService);
    return listCompletableFuture.join();
  }

  public List<Collaborator> getTopTenCollaborators(List<List<Collaborator>> intermediateList) {
    List<Collaborator> finalList = new ArrayList<>();
    for (int i = 0; i < intermediateList.size(); i++) {
      finalList.addAll(intermediateList.get(i));
    }
    Collections.sort(finalList);
    if (finalList.size() > 10)
      return finalList.subList(0, 9);
    else
      return finalList;
  }

  public List<Collaborator> getTopTenCollaboratorsForRepo(List<Collaborator> allCollaborators) {
    if (allCollaborators.size() >= 10)
      return allCollaborators.subList(0, 9);
    else
      return allCollaborators;
  }

}
