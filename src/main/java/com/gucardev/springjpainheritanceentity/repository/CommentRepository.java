package com.gucardev.springjpainheritanceentity.repository;

import com.gucardev.springjpainheritanceentity.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
