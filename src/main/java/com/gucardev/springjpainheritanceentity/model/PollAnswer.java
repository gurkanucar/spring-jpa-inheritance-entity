package com.gucardev.springjpainheritanceentity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PollAnswer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "poll_post_id")
  @JsonBackReference
  private PollPost pollPost;

  private String answer;

  @ManyToMany
  @JoinTable(
      name = "user_poll_answer",
      joinColumns = @JoinColumn(name = "poll_answer_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> usersWhoVoted;
}
