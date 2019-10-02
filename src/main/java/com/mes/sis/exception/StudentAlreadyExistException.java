package com.mes.sis.exception;

public final class StudentAlreadyExistException extends RuntimeException {

  private static final long serialVersionUID = 5861310537366287163L;

  public StudentAlreadyExistException() {
    super();
  }

  public StudentAlreadyExistException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public StudentAlreadyExistException(final String message) {
    super(message);
  }

  public StudentAlreadyExistException(final Throwable cause) {
    super(cause);
  }
}
