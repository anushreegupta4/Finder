package com.th.doctorlocator.model;

public class Collaborator implements Comparable<Collaborator> {
  private String login;
  private Long id;
  private Long contributions;

  public Collaborator(String login, Long id, Long contributions) {
    this.login = login;
    this.id = id;
    this.contributions = contributions;
  }

  public Long getContributions() {
    return contributions;
  }

  @Override
  public int compareTo(Collaborator o) {
    return (int) (o.getContributions() - this.getContributions());
  }
}
