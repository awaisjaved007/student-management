package com.mes.sis.exception;

public final class StudentNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5861310537366287163L;

    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(final String message) {
        super(message);
    }

    public StudentNotFoundException(final Throwable cause) {
        super(cause);
    }
}
