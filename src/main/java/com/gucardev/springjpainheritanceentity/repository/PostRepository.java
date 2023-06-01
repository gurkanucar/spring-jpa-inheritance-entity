package com.gucardev.springjpainheritanceentity.repository;

import com.gucardev.springjpainheritanceentity.model.ImagePost;
import com.gucardev.springjpainheritanceentity.model.PollPost;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.TextPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  @Query("SELECT p FROM TextPost p")
  List<TextPost> findAllTextPosts();

  @Query("SELECT p FROM ImagePost p")
  List<ImagePost> findAllImagePosts();

  //  @Query("SELECT p FROM VideoPost p")
  //  List<VideoPost> findAllVideoPosts();

  @Query("SELECT p FROM PollPost p")
  List<PollPost> findAllPollPosts();
}
