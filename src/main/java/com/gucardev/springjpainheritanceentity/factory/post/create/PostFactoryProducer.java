package com.gucardev.springjpainheritanceentity.factory.post.create;

import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PostFactoryProducer {
  private final Map<String, PostFactory> factoryMap;

  public PostFactoryProducer(Map<String, PostFactory> factoryMap) {
    this.factoryMap = factoryMap;
  }

  public PostFactory getFactoryByType(PostType type) {
    PostFactory factory = factoryMap.get(type.factoryBeanName());
    if (factory == null) {
      throw new IllegalArgumentException("Invalid PostType provided");
    }
    return factory;
  }
}
