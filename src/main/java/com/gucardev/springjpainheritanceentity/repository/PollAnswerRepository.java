package com.gucardev.springjpainheritanceentity.repository;

import com.gucardev.springjpainheritanceentity.model.PollAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollAnswerRepository extends JpaRepository<PollAnswer, Long> {

}
