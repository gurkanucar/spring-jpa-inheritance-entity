package com.gucardev.springjpainheritanceentity.factory.post.update;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.dto.PostDTO.PollAnswerDTO;
import com.gucardev.springjpainheritanceentity.model.PollAnswer;
import com.gucardev.springjpainheritanceentity.model.PollPost;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.repository.PollAnswerRepository;
import com.gucardev.springjpainheritanceentity.repository.PostRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PollPostUpdater implements PostUpdater {

  @Autowired private PollAnswerRepository pollAnswerRepository;
  @Autowired private PostRepository postRepository;

  @Override
  public Post update(Post post, PostDTO postDto) {
    PollPost pollPost = (PollPost) post;
    pollPost.setContent(postDto.getContent());

    Map<Long, PollAnswerDTO> pollAnswerMap = new HashMap<>();
    for (PollAnswerDTO dto : postDto.getPollAnswers()) {
      if (dto.getId() != null) {
        pollAnswerMap.put(dto.getId(), dto);
      }
    }

    // Existing PollAnswer entities that need to be updated
    List<PollAnswer> pollAnswersToUpdate = pollPost.getPollAnswers().stream()
        .filter(pollAnswer -> pollAnswerMap.containsKey(pollAnswer.getId()))
        .collect(Collectors.toList());

    // Update existing PollAnswer entities and save them
    for (PollAnswer pollAnswer : pollAnswersToUpdate) {
      PollAnswerDTO dto = pollAnswerMap.get(pollAnswer.getId());
      pollAnswer.setAnswer(dto.getAnswer());
      pollAnswerRepository.save(pollAnswer);
    }

    // Existing PollAnswer entities that need to be removed
    List<PollAnswer> pollAnswersToRemove = pollPost.getPollAnswers().stream()
        .filter(pollAnswer -> !pollAnswerMap.containsKey(pollAnswer.getId()))
        .collect(Collectors.toList());

    // Remove existing PollAnswer entities from the post and delete them
    for (PollAnswer pollAnswer : pollAnswersToRemove) {
      pollPost.getPollAnswers().remove(pollAnswer);
      pollAnswerRepository.delete(pollAnswer);
    }

    // New PollAnswer entities that need to be added
    List<PollAnswerDTO> pollAnswersToAdd = postDto.getPollAnswers().stream()
        .filter(dto -> dto.getId() == null)
        .collect(Collectors.toList());

    // Add new PollAnswer entities to the post and save them
    for (PollAnswerDTO dto : pollAnswersToAdd) {
      PollAnswer pollAnswer = new PollAnswer();
      pollAnswer.setAnswer(dto.getAnswer());
      pollAnswer.setPollPost(pollPost);
      pollAnswerRepository.save(pollAnswer);
      pollPost.getPollAnswers().add(pollAnswer);
    }

    return postRepository.save(pollPost);
  }
}
