package com.gucardev.springjpainheritanceentity.service;

import com.gucardev.springjpainheritanceentity.model.Comment;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.User;
import com.gucardev.springjpainheritanceentity.repository.CommentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final UserService userService;
  private final PostService postService;

  public CommentService(
      CommentRepository commentRepository, UserService userService, PostService postService) {
    this.commentRepository = commentRepository;
    this.userService = userService;
    this.postService = postService;
  }

  public List<Comment> getAllComments() {
    return commentRepository.findAll();
  }

  public Comment getComment(Long id) {
    return commentRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
  }

  public Comment saveComment(Long userId, Long postId, Comment comment) {
    User user = userService.getUser(userId);
    Post post = postService.getPost(postId);

    comment.setUser(user);
    comment.setPost(post);

    return commentRepository.save(comment);
  }

  public Comment updateComment(Long id, Comment updatedComment) {
    return commentRepository
        .findById(id)
        .map(
            comment -> {
              comment.setContent(updatedComment.getContent());
              return commentRepository.save(comment);
            })
        .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
  }

  public void deleteComment(Long id) {
    if (!commentRepository.existsById(id)) {
      throw new IllegalArgumentException("Comment not found");
    }
    commentRepository.deleteById(id);
  }
}
