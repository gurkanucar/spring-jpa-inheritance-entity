package com.gucardev.springjpainheritanceentity.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.dto.VoteDTO;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.service.PostService;
import com.gucardev.springjpainheritanceentity.service.VotingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
  private final PostService postService;
  private final VotingService votingService;

  public PostController(PostService postService, VotingService votingService) {
    this.postService = postService;
    this.votingService = votingService;
  }

  @GetMapping
  public ResponseEntity<Object> get(@RequestParam(required = false) PostType postType) {
    return ResponseEntity.ok(postService.getPostsByTypeDTO(postType));
  }

  @PutMapping
  public ResponseEntity<Post> updatePost(@RequestBody PostDTO postDto) {
    Post post = postService.updatePost(postDto);
    return ResponseEntity.ok(post);
  }

  @PostMapping
  public ResponseEntity<Post> createPost(@RequestBody PostDTO postDto) {
    Post post = postService.createPost(postDto);
    return ResponseEntity.ok(post);
  }

  @PostMapping("/vote")
  public ResponseEntity<?> vote(@RequestBody VoteDTO voteDto) {
    votingService.vote(voteDto);
    return ResponseEntity.ok().build();
  }
}
