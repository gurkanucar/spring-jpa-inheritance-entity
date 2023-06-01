package com.gucardev.springjpainheritanceentity.dto;

import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
  private PostType type;
  private Long userId;
  private String text;
  private String imageUrl;
  private String videoUrl;
  private List<PollAnswerDTO> pollAnswers;

  @Getter
  @Setter
  public static class PollAnswerDTO {
    private String answer;
    private Long voteCount;

  }
}
