package com.gucardev.springjpainheritanceentity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {
  private Long postId;
  private Long userId;
  private PostType type;
  private UserOwnerDTO owner;
  private String content;
  private String imageUrl;
  private String videoUrl;
  private List<PollAnswerDTO> pollAnswers;

  @Getter
  @Setter
  public static class PollAnswerDTO {
    private Long id;
    private String answer;
    private Integer voteCount;
    private List<UserSimpleDTO> usersWhoVoted;
  }

  @Getter
  @Setter
  public static class UserOwnerDTO {
    private Long id;
    private String username;
    private String displayName;
  }

  @Getter
  @Setter
  public static class UserSimpleDTO {
    private Long id;
    private String username;
  }
}
