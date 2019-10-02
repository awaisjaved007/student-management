package com.mes.sis.vo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassVO {

  @NotBlank(message = "Class name must be provided.")
  private String name;

  @NotBlank(message = "Section must be provided.")
  private String section;
}
