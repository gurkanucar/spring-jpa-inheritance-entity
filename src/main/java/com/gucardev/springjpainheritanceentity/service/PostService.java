package com.gucardev.springjpainheritanceentity.service;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import com.gucardev.springjpainheritanceentity.model.ImagePost;
import com.gucardev.springjpainheritanceentity.model.PollAnswer;
import com.gucardev.springjpainheritanceentity.model.PollPost;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.TextPost;
import com.gucardev.springjpainheritanceentity.model.User;
import com.gucardev.springjpainheritanceentity.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;
  private final UserService userService;

  public PostService(PostRepository postRepository, UserService userService) {
    this.postRepository = postRepository;
    this.userService = userService;
  }

  public List<? extends Post> getPostsByType(PostType postType) {
    switch (postType) {
      case TEXT:
        return postRepository.findAllTextPosts();
      case IMAGE:
        return postRepository.findAllImagePosts();
      case POLL:
        return postRepository.findAllPollPosts();
      default:
        throw new IllegalArgumentException("Invalid PostType");
    }
  }

  public Post createPost(PostDTO postDto) {
    User user = userService.getUser(postDto.getUserId());

    Post post;
    switch (postDto.getType()) {
      case TEXT:
        post = new TextPost();
        ((TextPost) post).setText(postDto.getText());
        break;
      case IMAGE:
        post = new ImagePost();
        ((ImagePost) post).setImageUrl(postDto.getImageUrl());
        break;
      case POLL:
        post = new PollPost();
        List<PollAnswer> pollAnswers =
            postDto.getPollAnswers().stream()
                .map(
                    dto -> {
                      PollAnswer pollAnswer = new PollAnswer();
                      pollAnswer.setAnswer(dto.getAnswer());
                      return pollAnswer;
                    })
                .collect(Collectors.toList());
        ((PollPost) post).setPollAnswers(pollAnswers);
        break;
      default:
        throw new IllegalArgumentException("Invalid PostType provided");
    }

    post.setUser(user);
    return postRepository.save(post);
  }
}
