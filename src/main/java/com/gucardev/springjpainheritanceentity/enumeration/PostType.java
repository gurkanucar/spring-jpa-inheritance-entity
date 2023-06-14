package com.gucardev.springjpainheritanceentity.enumeration;

public enum PostType {
  TEXT(PostType.TEXT_FACTORY_NAME),
  IMAGE(PostType.IMAGE_FACTORY_NAME),
  VIDEO(PostType.VIDEO_FACTORY_NAME),
  POLL(PostType.POLL_FACTORY_NAME);

  public static final String TEXT_FACTORY_NAME = "textPostFactory";
  public static final String IMAGE_FACTORY_NAME = "imagePostFactory";
  public static final String VIDEO_FACTORY_NAME = "videoPostFactory";
  public static final String POLL_FACTORY_NAME = "pollPostFactory";

  private final String factoryBeanName;

  PostType(String factoryBeanName) { this.factoryBeanName = factoryBeanName; }
  public String factoryBeanName() { return factoryBeanName; }
}
