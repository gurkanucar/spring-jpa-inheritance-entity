package com.gucardev.springjpainheritanceentity.factory.post.create;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.TextPost;

public class TextPostFactory implements PostFactory {

  @Override
  public Post createPost(PostDTO postDto) {
    TextPost post = new TextPost();
    post.setContent(postDto.getContent());
    return post;
  }
}
