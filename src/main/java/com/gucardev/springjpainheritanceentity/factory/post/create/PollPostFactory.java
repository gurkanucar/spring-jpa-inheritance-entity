package com.gucardev.springjpainheritanceentity.factory.post.create;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.model.PollAnswer;
import com.gucardev.springjpainheritanceentity.model.PollPost;
import com.gucardev.springjpainheritanceentity.model.Post;
import java.util.List;
import java.util.stream.Collectors;

public class PollPostFactory implements PostFactory {

  @Override
  public Post createPost(PostDTO postDto) {
    PollPost post = new PollPost();
    List<PollAnswer> pollAnswers =
        postDto.getPollAnswers().stream()
            .map(
                dto -> {
                  PollAnswer pollAnswer = new PollAnswer();
                  pollAnswer.setAnswer(dto.getAnswer());
                  pollAnswer.setPollPost(post);
                  return pollAnswer;
                })
            .collect(Collectors.toList());
    post.setPollAnswers(pollAnswers);
    return post;
  }
}