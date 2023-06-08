package com.gucardev.springjpainheritanceentity.factory.post.update;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.model.ImagePost;
import com.gucardev.springjpainheritanceentity.model.Post;
import org.springframework.stereotype.Service;

@Service
public class ImagePostUpdater implements PostUpdater {

  @Override
  public Post update(Post post, PostDTO postDto) {
    ((ImagePost) post).setContent(postDto.getContent());
    ((ImagePost) post).setImageUrl(postDto.getImageUrl());
    return post;
  }
}
