package com.gucardev.springjpainheritanceentity.enumeration;

public enum PostType {
  TEXT,
  IMAGE,
  VIDEO,
  POLL;

  public static PostType getEnumByString(String str) {
    for (PostType type : PostType.values()) {
      if (type.name().equalsIgnoreCase(str)) return type;
    }
    throw new IllegalArgumentException("Not found with text " + str);
  }
}
