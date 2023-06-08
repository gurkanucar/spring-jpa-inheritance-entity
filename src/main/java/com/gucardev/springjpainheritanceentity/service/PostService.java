package com.gucardev.springjpainheritanceentity.service;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import com.gucardev.springjpainheritanceentity.factory.PostFactory;
import com.gucardev.springjpainheritanceentity.factory.PostFactoryProducer;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.User;
import com.gucardev.springjpainheritanceentity.repository.PostRepository;
import java.util.List;
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

  public Post createPost(PostDTO postDto) {
    User user = userService.getUser(postDto.getUserId());

    PostFactory postFactory = PostFactoryProducer.getFactory(postDto.getType());
    Post post = postFactory.createPost(postDto);

    post.setUser(user);
    return postRepository.save(post);
  }
}
