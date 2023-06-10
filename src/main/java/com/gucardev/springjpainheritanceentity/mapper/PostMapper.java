package com.gucardev.springjpainheritanceentity.mapper;

import com.gucardev.springjpainheritanceentity.dto.PostDTO;
import com.gucardev.springjpainheritanceentity.enumeration.PostType;
import com.gucardev.springjpainheritanceentity.model.ImagePost;
import com.gucardev.springjpainheritanceentity.model.PollAnswer;
import com.gucardev.springjpainheritanceentity.model.PollPost;
import com.gucardev.springjpainheritanceentity.model.Post;
import com.gucardev.springjpainheritanceentity.model.TextPost;
import com.gucardev.springjpainheritanceentity.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  @Mapping(target = "postId", source = "id")
  @Mapping(target = "owner", source = "user")
  @Mapping(target = "content", source = "content")
  PostDTO toDTOBase(Post post);

  default PostDTO toDTO(TextPost post) {
    PostDTO postDTO = toDTOBase(post);
    postDTO.setType(PostType.TEXT);
    return postDTO;
  }

  default PostDTO toDTO(ImagePost post) {
    PostDTO postDTO = toDTOBase(post);
    postDTO.setType(PostType.IMAGE);
    postDTO.setImageUrl(post.getImageUrl());
    return postDTO;
  }

  @Mapping(target = "pollAnswers", source = "pollAnswers", qualifiedByName = "toPollAnswerDTOList")
  default PostDTO toDTO(PollPost post) {
    PostDTO postDTO = toDTOBase(post);
    postDTO.setType(PostType.POLL);
    postDTO.setPollAnswers(toPollAnswerDTOList(post.getPollAnswers()));
    return postDTO;
  }

  @Mapping(target = "id", source = "id")
  @Mapping(target = "username", source = "username")
  @Mapping(target = "displayName", source = "displayName")
  PostDTO.UserOwnerDTO userToUserOwnerDTO(User user);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "answer", source = "answer")
  @Mapping(target = "voteCount", expression = "java(pollAnswer.getUsersWhoVoted().size())")
  @Mapping(
      target = "usersWhoVoted",
      source = "usersWhoVoted",
      qualifiedByName = "toUserSimpleDTOList")
  PostDTO.PollAnswerDTO toPollAnswerDTO(PollAnswer pollAnswer);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "username", source = "username")
  PostDTO.UserSimpleDTO userToUserSimpleDTO(User user);

  List<PostDTO.PollAnswerDTO> toPollAnswerDTOList(List<PollAnswer> pollAnswers);

  @Named("toUserSimpleDTOList")
  List<PostDTO.UserSimpleDTO> toUserSimpleDTOList(List<User> users);

  default PostDTO toDTO(Post post) {
    if (post instanceof TextPost) {
      return toDTO((TextPost) post);
    } else if (post instanceof ImagePost) {
      return toDTO((ImagePost) post);
    } else if (post instanceof PollPost) {
      return toDTO((PollPost) post);
    } else {
      throw new IllegalArgumentException("Invalid post type");
    }
  }
}
