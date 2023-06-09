package com.gucardev.springjpainheritanceentity.controller;

import com.gucardev.springjpainheritanceentity.model.Comment;
import com.gucardev.springjpainheritanceentity.service.CommentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/")
  public ResponseEntity<List<Comment>> getAllComments() {
    List<Comment> comments = commentService.getAllComments();
    return ResponseEntity.ok(comments);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Comment> getComment(@PathVariable Long id) {
    Comment comment = commentService.getComment(id);
    return ResponseEntity.ok(comment);
  }

  @PostMapping("/")
  public ResponseEntity<Comment> saveComment(
      @RequestParam Long userId, @RequestParam Long postId, @RequestBody Comment comment) {
    Comment savedComment = commentService.saveComment(userId, postId, comment);
    return ResponseEntity.ok(savedComment);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Comment> updateComment(
      @PathVariable Long id, @RequestBody Comment updatedComment) {
    Comment comment = commentService.updateComment(id, updatedComment);
    return ResponseEntity.ok(comment);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
    commentService.deleteComment(id);
    return ResponseEntity.noContent().build();
  }
}
