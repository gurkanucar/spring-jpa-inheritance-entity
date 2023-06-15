package com.gucardev.springjpainheritanceentity.factory.post.create;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.TextPost;
import org.springframework.stereotype.Component;

@Component(PostType.TEXT_FACTORY_NAME)
public class TextPostFactory implements PostFactory {

  @Override
  public Post createPost(PostDTO postDto) {
    TextPost post = new TextPost();
    post.setContent(postDto.getContent());
    return post;
  }
}
