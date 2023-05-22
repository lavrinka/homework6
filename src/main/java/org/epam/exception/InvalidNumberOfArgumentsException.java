package org.epam.exception;

public class InvalidNumberOfArgumentsException extends RuntimeException {

  public InvalidNumberOfArgumentsException() {
    super("Invalid number of params");
  }

}
