package com.mes.sis.service;

import com.mes.sis.domain.Class;
import com.mes.sis.exception.ClassAlreadyExistException;
import com.mes.sis.repository.ClassRepository;
import com.mes.sis.repository.EnrollmentRepository;
import com.mes.sis.repository.StudentRepository;
import com.mes.sis.utils.CommonUtils;
import com.mes.sis.vo.request.ClassVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class AdminService implements IAdminService {

  private final ClassRepository classRepository;

  private final StudentRepository studentRepository;

  private final EnrollmentRepository enrollmentRepository;

  @Autowired
  public AdminService(
      final ClassRepository classRepository,
      final StudentRepository studentRepository,
      final EnrollmentRepository enrollmentRepository) {
    this.classRepository = classRepository;
    this.studentRepository = studentRepository;
    this.enrollmentRepository = enrollmentRepository;
  }

  @Override
  @Transactional
  public void addClass(ClassVO classVO) {
    fetchIfExists(classVO);
    this.classRepository.save(new Class(classVO));
  }

  private Class fetchIfExists(ClassVO classVO) {
    CommonUtils.debug(log, "###ClassVO###[", classVO.toString(), "]");
    Optional<Class> optionalClass =
        this.classRepository.findFirstByNameAndSection(classVO.getName(), classVO.getSection());
    if (optionalClass.isPresent()) {
      throw new ClassAlreadyExistException();
    }
    return optionalClass.get();
  }

  @Override
  public void removeClass(Long classId) {}
}
