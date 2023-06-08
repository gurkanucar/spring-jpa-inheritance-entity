package com.gucardev.springjpainheritanceentity.factory.post.update;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.TextPost;
import org.springframework.stereotype.Service;

@Service
public class TextPostUpdater implements PostUpdater {

  @Override
  public Post update(Post post, PostDTO postDto) {
    ((TextPost) post).setContent(postDto.getContent());
    return post;
  }
}
