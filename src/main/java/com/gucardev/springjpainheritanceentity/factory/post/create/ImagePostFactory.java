package com.gucardev.springjpainheritanceentity.factory.post.create;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import com.gucardev.springjpainheritanceentity.model.ImagePost;
import com.gucardev.springjpainheritanceentity.model.Post;
import org.springframework.stereotype.Component;

@Component(PostType.IMAGE_FACTORY_NAME)
public class ImagePostFactory implements PostFactory {

  @Override
  public Post createPost(PostDTO postDto) {
    ImagePost post = new ImagePost();
    post.setImageUrl(postDto.getImageUrl());
    post.setContent(postDto.getContent());
    return post;
  }
}
