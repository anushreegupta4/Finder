package com.th.doctorlocator.controller;

import com.sun.istack.internal.NotNull;
import com.th.doctorlocator.model.Collaborator;
import com.th.doctorlocator.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/collaborator/")
public class CollaboratorController {

  private CollaboratorService collaboratorService;

  @Autowired
  public CollaboratorController(CollaboratorService collaboratorService) {
    this.collaboratorService = collaboratorService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "topTen/{organisation}")
  public ResponseEntity<List<Collaborator>> getTopTenCollaboratorsForOrganisation(@NotNull @PathVariable String organisation) {
    List<Collaborator> collaborators = collaboratorService.getTopTenCollaboratorsForOrganisation(organisation);
    return ResponseEntity.ok(collaborators);
  }
}
