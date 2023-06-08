package com.gucardev.springjpainheritanceentity.controller;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }


  @GetMapping
  public ResponseEntity<Object> get(@RequestParam(required = false) PostType postType) {
    return ResponseEntity.ok(postService.getPostsByType(postType));
  }

  @PostMapping
  public ResponseEntity<Post> createPost(@RequestBody PostDTO postDto) {
    Post post = postService.createPost(postDto);
    return ResponseEntity.ok(post);
  }
}
