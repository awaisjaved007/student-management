package com.mes.sis.service;

import com.mes.sis.domain.Class;
import com.mes.sis.domain.Enrollment;
import com.mes.sis.domain.Student;
import com.mes.sis.exception.ClassAlreadyExistException;
import com.mes.sis.exception.StudentAlreadyExistException;
import com.mes.sis.exception.StudentNotFoundException;
import com.mes.sis.repository.ClassRepository;
import com.mes.sis.repository.EnrollmentRepository;
import com.mes.sis.repository.StudentRepository;
import com.mes.sis.utils.CommonUtils;
import com.mes.sis.vo.request.ClassVO;
import com.mes.sis.vo.request.EnrollmentVO;
import com.mes.sis.vo.request.StudentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AdminService implements IAdminService {

  private final ClassRepository classRepository;

  private final StudentRepository studentRepository;

  private final EnrollmentRepository enrollmentRepository;

  private final MessageSource messageSource;

  @Autowired
  public AdminService(
      final ClassRepository classRepository,
      final StudentRepository studentRepository,
      final EnrollmentRepository enrollmentRepository,
      final MessageSource messageSource) {
    this.classRepository = classRepository;
    this.studentRepository = studentRepository;
    this.enrollmentRepository = enrollmentRepository;
    this.messageSource = messageSource;
  }

  /*Adding new class instance*/
  @Override
  @Transactional
  public void addClass(ClassVO classVO) {
    // If class already exists then throws exception
    isClassExists(classVO);

    this.classRepository.save(new Class(classVO));
  }

  private void isClassExists(ClassVO classVO) {
    CommonUtils.debug(log, "###ClassVO###[", classVO.toString(), "]");
    Optional<Class> optionalClass = this.classRepository.findFirstByName(classVO.getName());
    if (optionalClass.isPresent()) {
      throw new ClassAlreadyExistException();
    }
  }

  private Class fetchClassById(Long id) {
    Optional<Class> optionalClass = this.classRepository.findFirstById(id);
    if (!optionalClass.isPresent()) {
      throw new ClassAlreadyExistException();
    }
    return optionalClass.get();
  }

  /** @return all classes for display */
  @Override
  @Transactional(readOnly = true)
  public List<Class> getAllClasses() {
    return this.classRepository.findAll(Sort.by("id"));
  }

  @Override
  public Student addNewStudent(StudentVO studentVO) {
    isStudentExists(studentVO);
    return this.studentRepository.save(new Student(studentVO));
  }

  private void isStudentExists(StudentVO studentVO) {
    CommonUtils.debug(log, "###StudentVO###[", studentVO.toString(), "]");
    Optional<Student> optionalClass = this.studentRepository.findFirstByCnic(studentVO.getCnic());
    if (optionalClass.isPresent()) {
      throw new StudentAlreadyExistException();
    }
  }

  /**
   * @param cnic Fetchig all Students by CNIC
   * @return
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<Student> findStudentByCnic(final String cnic) {
    return this.studentRepository.findFirstByCnic(cnic);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Student> findStudentByRegistrationId(final String registrationId) {
    return this.studentRepository.findFirstByRegistrationId(registrationId);
  }

  /** @param enrollmentVO Registration of Student in class */
  @Override
  @Transactional
  public void registerStudentInClass(final EnrollmentVO enrollmentVO) {
    Optional<Student> student = this.findStudentByCnic(enrollmentVO.getCnic());
    Class studentClass;
    if (student.isPresent()) {
      studentClass = this.fetchClassById(enrollmentVO.getClassId());
      this.enrollmentRepository.save(new Enrollment(student.get(), studentClass));
    } else {
      throw new StudentNotFoundException();
    }
  }

  /** @param enrollmentVO De-Registration of Student from specific class */
  @Transactional
  @Override
  public void deRegisterStudentFromClass(final EnrollmentVO enrollmentVO) {
    Optional<Enrollment> records =
        this.enrollmentRepository.findFirstByClassId_IdAndStudent_Cnic(
            enrollmentVO.getClassId(), enrollmentVO.getCnic());

    if (!records.isPresent()) {
      throw new StudentNotFoundException(
          messageSource.getMessage("message.student.error.deregister", null, null));
    } else {
      records.get().setStudent(null);
      records.get().setClassId(null);
      this.enrollmentRepository.delete(records.get());
    }
  }
}
