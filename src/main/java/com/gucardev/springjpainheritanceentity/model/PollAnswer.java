package com.gucardev.springjpainheritanceentity.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class PollAnswer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "poll_post_id")
  private PollPost pollPost;

  private String answer;

  @ManyToMany
  @JoinTable(
      name = "user_poll_answer",
      joinColumns = @JoinColumn(name = "poll_answer_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private List<User> usersWhoVoted;
}