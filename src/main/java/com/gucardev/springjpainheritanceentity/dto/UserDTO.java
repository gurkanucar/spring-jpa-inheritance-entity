package com.gucardev.springjpainheritanceentity.dto;

import com.gucardev.springjpainheritanceentity.model.Comment;
import com.gucardev.springjpainheritanceentity.model.PollAnswer;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private Long id;

  private String username;
  private String displayName;
  private String email;

  private List<Comment> comments;

  private List<PollAnswer> votedAnswers;
}
