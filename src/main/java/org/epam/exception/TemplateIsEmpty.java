package org.epam.exception;

public class TemplateIsEmpty extends RuntimeException {

  public TemplateIsEmpty() {
    super("The is no template");
  }

}