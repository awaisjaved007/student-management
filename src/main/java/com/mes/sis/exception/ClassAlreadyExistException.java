package com.mes.sis.exception;

public final class ClassAlreadyExistException extends RuntimeException {

  private static final long serialVersionUID = 5861310537366287163L;

  public ClassAlreadyExistException() {
    super();
  }

  public ClassAlreadyExistException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ClassAlreadyExistException(final String message) {
    super(message);
  }

  public ClassAlreadyExistException(final Throwable cause) {
    super(cause);
  }
}
