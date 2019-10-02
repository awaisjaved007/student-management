package com.mes.sis.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EnrollmentVO {

  @NotNull(message = "{message.enrollment.vo.classId}")
  private Long classId;

  @NotBlank(message = "{message.enrollment.vo.cnic}")
  private String cnic;
}
