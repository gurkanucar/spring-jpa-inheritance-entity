package com.gucardev.springjpainheritanceentity.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "post_id")
@Getter @Setter
public class TextPost extends Post {

}
