package com.mes.sis.service;

import com.mes.sis.domain.Class;
import com.mes.sis.domain.Student;
import com.mes.sis.vo.request.ClassVO;
import com.mes.sis.vo.request.EnrollmentVO;
import com.mes.sis.vo.request.StudentVO;

import java.util.List;
import java.util.Optional;

public interface IAdminService {

  void addClass(ClassVO classVO);

  Student addNewStudent(StudentVO studentVO);

  List<Class> getAllClasses();

  Optional<Student> findStudentByCnic(final String cnic);

  Optional<Student> findStudentByRegistrationId(final String registrationId);

  void registerStudentInClass(final EnrollmentVO enrollmentVO);

  void deRegisterStudentFromClass(final EnrollmentVO enrollmentVO);
}
