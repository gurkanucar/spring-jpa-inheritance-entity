package com.gucardev.springjpainheritanceentity.factory.post.create;

import com.gucardev.springjpainheritanceentity.enumeration.PostType;

public class PostFactoryProducer {

  public static PostFactory getFactory(PostType type) {
    switch (type) {
      case TEXT:
        return new TextPostFactory();
      case IMAGE:
        return new ImagePostFactory();
      case POLL:
        return new PollPostFactory();
      default:
        throw new IllegalArgumentException("Invalid PostType provided");
    }
  }
}
