package com.gucardev.springjpainheritanceentity.factory.post.create;

import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PostFactoryProducer {
  private final Map<PostType, PostFactory> factoryMap;

  public PostFactoryProducer(List<PostFactory> factories) {
    this.factoryMap =
        factories.stream().collect(Collectors.toMap(PostFactory::getPostType, Function.identity()));
  }

  public PostFactory getFactoryByType(PostType type) {
    PostFactory factory = factoryMap.get(type);
    if (factory == null) {
      throw new IllegalArgumentException("Invalid PostType provided");
    }
    return factory;
  }
}
