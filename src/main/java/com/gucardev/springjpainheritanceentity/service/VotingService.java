package com.gucardev.springjpainheritanceentity.service;

import com.gucardev.springjpainheritanceentity.dto.VoteDTO;
import com.gucardev.springjpainheritanceentity.model.PollAnswer;
import com.gucardev.springjpainheritanceentity.model.User;
import com.gucardev.springjpainheritanceentity.repository.PollAnswerRepository;
import com.gucardev.springjpainheritanceentity.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class VotingService {
  private final UserRepository userRepository;

  private final PollAnswerRepository pollAnswerRepository;

  public VotingService(UserRepository userRepository, PollAnswerRepository pollAnswerRepository) {
    this.userRepository = userRepository;
    this.pollAnswerRepository = pollAnswerRepository;
  }

  public void vote(VoteDTO voteDto) {
    User user =
        userRepository
            .findById(voteDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    PollAnswer pollAnswer =
        pollAnswerRepository
            .findById(voteDto.getPollAnswerId())
            .orElseThrow(() -> new IllegalArgumentException("Poll answer not found"));

    if (user.getVotedAnswers().contains(pollAnswer)) {
      throw new IllegalArgumentException("User already voted for this answer");
    }

    user.getVotedAnswers().add(pollAnswer);
    pollAnswer.getUsersWhoVoted().add(user);

    userRepository.save(user);
    pollAnswerRepository.save(pollAnswer);
  }
}
