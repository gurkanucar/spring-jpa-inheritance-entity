package com.gucardev.springjpainheritanceentity.factory;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.model.Post;

public interface PostFactory {
  Post createPost(PostDTO postDto);
}
