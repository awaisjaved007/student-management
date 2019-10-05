package com.mes.sis.controller;

import com.mes.sis.service.AdminService;
import com.mes.sis.service.IAdminService;
import com.mes.sis.utils.GenericResponse;
import com.mes.sis.vo.request.ClassVO;
import com.mes.sis.vo.request.EnrollmentVO;
import com.mes.sis.vo.request.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/admin")
@RestController
public class AdminController {

  private final IAdminService adminService;

  private MessageSource messageSource;

  @Autowired
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

  @GetMapping("/all/classes")
  public GenericResponse getAllClasses(final HttpServletRequest request) {
    return new GenericResponse(
        this.adminService.getAllClasses(),
        messageSource.getMessage("message.success", null, request.getLocale()));
  }

  @PostMapping("/add/student")
  public GenericResponse addNewStudent(
      @Valid @RequestBody final StudentVO data, final HttpServletRequest request) {

    return new GenericResponse(
        this.adminService.addNewStudent(data),
        messageSource.getMessage("message.student.success", null, request.getLocale()));
  }

  @GetMapping("/student/cnic/{cnic}")
  public GenericResponse findStudentByCnic(
      @PathVariable final String cnic, final HttpServletRequest request) {
    return new GenericResponse(
        this.adminService.findStudentByCnic(cnic),
        messageSource.getMessage("message.success", null, request.getLocale()));
  }

  @GetMapping("/student/registration-id/{registrationId}")
  public GenericResponse findStudentByRegistrationId(
      @PathVariable final String registrationId, final HttpServletRequest request) {
    return new GenericResponse(
        this.adminService.findStudentByRegistrationId(registrationId),
        messageSource.getMessage("message.success", null, request.getLocale()));
  }

  @PostMapping("/register/student")
  public GenericResponse registerStudentByCnic(
      @Valid @RequestBody final EnrollmentVO data, final HttpServletRequest request) {
    this.adminService.registerStudentInClass(data);
    return new GenericResponse(
        messageSource.getMessage("message.student.register.success", null, request.getLocale()));
  }

  @PutMapping("/de-register/student")
  public GenericResponse deRegisterStudentByCnic(
      @Valid @RequestBody final EnrollmentVO data, final HttpServletRequest request) {
    this.adminService.deRegisterStudentFromClass(data);
    return new GenericResponse(
        messageSource.getMessage(
            "message.student.registration.revoke.success", null, request.getLocale()));
  }
}
