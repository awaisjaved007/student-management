package com.mes.sis.controller;

import com.mes.sis.service.AdminService;
import com.mes.sis.service.IAdminService;
import com.mes.sis.utils.GenericResponse;
import com.mes.sis.vo.request.ClassVO;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/admin")
@RestController
public class AdminController {

  private final IAdminService adminService;

  private MessageSource messageSource;

  public AdminController(final AdminService adminService, final MessageSource messageSource) {
    this.adminService = adminService;
    this.messageSource = messageSource;
  }

  @PostMapping("/add/class")
  public GenericResponse addClass(
      @Valid @RequestBody final ClassVO data, final HttpServletRequest request) {
    this.adminService.addClass(data);
    return new GenericResponse(
        messageSource.getMessage("message.class.regSucc", null, request.getLocale()));
  }
}
