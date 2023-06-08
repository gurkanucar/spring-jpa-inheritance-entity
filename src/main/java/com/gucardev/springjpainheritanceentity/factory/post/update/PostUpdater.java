package com.gucardev.springjpainheritanceentity.factory.post.update;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.model.Post;

public interface PostUpdater {
  Post update(Post post, PostDTO postDto);
}
