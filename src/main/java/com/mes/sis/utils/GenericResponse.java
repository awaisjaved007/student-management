package com.mes.sis.utils;

import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class GenericResponse {
  private String message;
  private String error;
  private Object data;

  public GenericResponse(final String message) {
    this.message = message;
  }

  public GenericResponse(final Object data,final String message) {
    this.data = data;
    this.message = message;
  }

  public GenericResponse(final String message, final String error) {
    this.message = message;
    this.error = error;
  }

  public GenericResponse(List<ObjectError> allErrors, String error) {
    this.error = error;
    String temp =
        allErrors.stream()
            .map(
                e -> {
                  if (e instanceof FieldError) {
                    return "{field:"
                        + ((FieldError) e).getField()
                        + ",defaultMessage:"
                        + e.getDefaultMessage()
                        + "}";
                  } else {
                    return "{object:"
                        + e.getObjectName()
                        + ",defaultMessage:"
                        + e.getDefaultMessage()
                        + "}";
                  }
                })
            .collect(Collectors.joining(","));
    this.message = "[" + temp + "]";
  }
}
