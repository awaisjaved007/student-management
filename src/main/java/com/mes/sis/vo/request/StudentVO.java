package com.mes.sis.vo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentVO {
  @Size(max = 20, min = 2, message = "{message.student.vo.first.name.length}")
  @NotBlank(message = "{message.student.vo.first.name}")
  private String firstName;

  @Size(max = 20, min = 2, message = "{message.student.vo.last.name.length}")
  @NotBlank(message = "{message.student.vo.last.name}")
  private String lastName;

  @Size(max = 20, min = 2, message = "message.student.vo.cnic.length")
  @NotBlank(message = "{message.student.vo.cnic}")
  private String cnic;
}
