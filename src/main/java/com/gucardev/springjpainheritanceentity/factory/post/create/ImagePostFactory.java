package com.gucardev.springjpainheritanceentity.factory.post.create;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.model.ImagePost;
import com.gucardev.springjpainheritanceentity.model.Post;

public class ImagePostFactory implements PostFactory {

  @Override
  public Post createPost(PostDTO postDto) {
    ImagePost post = new ImagePost();
    post.setImageUrl(postDto.getImageUrl());
    return post;
  }
}
