package com.gucardev.springjpainheritanceentity.service;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import com.gucardev.springjpainheritanceentity.factory.post.create.PostFactory;
import com.gucardev.springjpainheritanceentity.factory.post.create.PostFactoryProducer;
import com.gucardev.springjpainheritanceentity.factory.post.update.PostUpdater;
import com.gucardev.springjpainheritanceentity.factory.post.update.PostUpdaterFactory;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.User;
import com.gucardev.springjpainheritanceentity.repository.PostRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;
  private final PostUpdaterFactory postUpdaterFactory;
  private final UserService userService;

  public PostService(
      PostRepository postRepository,
      PostUpdaterFactory postUpdaterFactory,
      UserService userService) {
    this.postRepository = postRepository;
    this.postUpdaterFactory = postUpdaterFactory;
    this.userService = userService;
  }

  public List<? extends Post> getPostsByType(PostType postType) {
    if (postType == null) {
      return postRepository.findAll();
    }

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

  public Post updatePost(PostDTO postDto) {
    Post post = getPost(postDto.getPostId());
    PostUpdater updater = postUpdaterFactory.getPostUpdater(post);
    Post updatedPost = updater.update(post, postDto);
    return postRepository.save(updatedPost);
  }

  public Post createPost(PostDTO postDto) {
    User user = userService.getUser(postDto.getUserId());

    PostFactory postFactory = PostFactoryProducer.getFactory(postDto.getType());
    Post post = postFactory.createPost(postDto);

    post.setUser(user);
    return postRepository.save(post);
  }

  public Post getPost(Long postId) {
    return postRepository
        .findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("post not found"));
  }
}
