package com.gucardev.springjpainheritanceentity.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "post_id")
@Getter
@Setter
public class PollPost extends Post {
  @OneToMany(mappedBy = "pollPost")
  private List<PollAnswer> pollAnswers;
}