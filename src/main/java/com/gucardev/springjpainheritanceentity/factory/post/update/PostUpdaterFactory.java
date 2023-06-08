package com.gucardev.springjpainheritanceentity.factory.post.update;

import com.gucardev.springjpainheritanceentity.model.ImagePost;
import com.gucardev.springjpainheritanceentity.model.PollPost;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.TextPost;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PostUpdaterFactory {
  private final Map<String, PostUpdater> updaterMap;

  public PostUpdaterFactory(List<PostUpdater> updaters) {
    updaterMap =
        updaters.stream()
            .collect(
                Collectors.toMap(
                    updater -> updater.getClass().getSimpleName(), Function.identity()));
  }

  public PostUpdater getPostUpdater(Post post) {
    if (post instanceof TextPost) {
      return updaterMap.get("TextPostUpdater");
    } else if (post instanceof ImagePost) {
      return updaterMap.get("ImagePostUpdater");
    } else if (post instanceof PollPost) {
      return updaterMap.get("PollPostUpdater");
    } else {
      throw new IllegalArgumentException("Invalid post type");
    }
  }
}
