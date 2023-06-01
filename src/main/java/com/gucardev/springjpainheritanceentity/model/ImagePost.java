package com.gucardev.springjpainheritanceentity.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "post_id")
@Getter
@Setter
public class ImagePost extends Post {
  private String imageUrl;
}
