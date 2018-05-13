package com.th.doctorlocator.service;

import com.th.doctorlocator.model.Collaborator;
import com.th.doctorlocator.model.Repo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorServiceTest {

  @InjectMocks
  private CollaboratorService collaboratorService;
  @Mock
  private OrganisationService organisationService;
  @Mock
  private RepositoryService repositoryService;

  @Test
  public void getTopTenCollaboratorsTest() {
    List<List<Collaborator>> collabList = new ArrayList<>();
    List<Collaborator> list1 = new ArrayList<>();
    Collaborator c1 = new Collaborator("c1", 0l, 10l);
    Collaborator c2 = new Collaborator("c2", 0l, 9l);
    Collaborator c3 = new Collaborator("c3", 0l, 2l);
    list1.add(c1);
    list1.add(c2);
    list1.add(c3);

    List<Collaborator> list2 = new ArrayList<>();
    Collaborator d1 = new Collaborator("d1", 0l, 11l);
    Collaborator d2 = new Collaborator("d2", 0l, 1l);
    Collaborator d3 = new Collaborator("d3", 0l, 1l);
    list2.add(d1);
    list2.add(d2);
    list2.add(d3);
    collabList.add(list1);
    collabList.add(list2);
    List<Collaborator> expectedList = new ArrayList<>();
    expectedList.add(d1);
    expectedList.add(c1);
    expectedList.add(c2);
    expectedList.add(c3);
    expectedList.add(d2);
    expectedList.add(d3);
    List<Collaborator> actual = collaboratorService.getTopTenCollaborators(collabList);
    Assert.assertEquals(expectedList, actual);
  }

  @Test
  public void getTopTenCollaboratorsForOrganisationTest() {
    List<Repo> repos = new ArrayList<>();
    Repo repo1 = new Repo(1l, "repo1");
    Repo repo2 = new Repo(2l, "repo2");
    Repo repo3 = new Repo(3l, "repo3");
    repos.add(repo1);
    repos.add(repo2);
    repos.add(repo3);
    Collaborator collaborator1repo1 = new Collaborator("c1r1", 1l, 11l);
    Collaborator collaborator2repo1 = new Collaborator("c2r1", 1l, 10l);
    Collaborator collaborator3repo1 = new Collaborator("c3r1", 1l, 7l);

    List<Collaborator> collaboratorsRepo1 = new ArrayList<>();
    collaboratorsRepo1.add(collaborator1repo1);
    collaboratorsRepo1.add(collaborator2repo1);
    collaboratorsRepo1.add(collaborator3repo1);

    Collaborator collaborator1repo2 = new Collaborator("c1r2", 1l, 21l);
    Collaborator collaborator2repo2 = new Collaborator("c2r2", 1l, 9l);
    Collaborator collaborator3repo2 = new Collaborator("c3r2", 1l, 1l);
    List<Collaborator> collaboratorsRepo2 = new ArrayList<>();
    collaboratorsRepo2.add(collaborator1repo2);
    collaboratorsRepo2.add(collaborator2repo2);
    collaboratorsRepo2.add(collaborator3repo2);

    Collaborator collaborator1repo3 = new Collaborator("c1r3", 1l, 102l);
    Collaborator collaborator2repo3 = new Collaborator("c2r3", 1l, 3l);
    Collaborator collaborator3repo3 = new Collaborator("c3r3", 1l, 2l);
    List<Collaborator> collaboratorsRepo3 = new ArrayList<>();
    collaboratorsRepo3.add(collaborator1repo3);
    collaboratorsRepo3.add(collaborator2repo3);
    collaboratorsRepo3.add(collaborator3repo3);

    when(organisationService.getAllReposForOrganisation("microsoft")).thenReturn(repos);
    when(repositoryService.getAllCollaborators("microsoft", "repo1")).thenReturn(collaboratorsRepo1);
    when(repositoryService.getAllCollaborators("microsoft", "repo2")).thenReturn(collaboratorsRepo2);
    when(repositoryService.getAllCollaborators("microsoft", "repo3")).thenReturn(collaboratorsRepo3);

    List<Collaborator> expectedCollaboratorList = new ArrayList<>();
    expectedCollaboratorList.add(collaborator1repo3);
    expectedCollaboratorList.add(collaborator1repo2);
    expectedCollaboratorList.add(collaborator1repo1);
    expectedCollaboratorList.add(collaborator2repo1);
    expectedCollaboratorList.add(collaborator2repo2);
    expectedCollaboratorList.add(collaborator3repo1);
    expectedCollaboratorList.add(collaborator2repo3);
    expectedCollaboratorList.add(collaborator3repo3);
    expectedCollaboratorList.add(collaborator3repo2);
    List<Collaborator> newList = collaboratorService.getTopTenCollaboratorsForOrganisation("microsoft");
    Assert.assertEquals(newList, expectedCollaboratorList);
  }

}