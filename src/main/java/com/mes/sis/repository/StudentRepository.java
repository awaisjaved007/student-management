package com.mes.sis.repository;

import com.mes.sis.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findFirstByCnic(final String cnic);

  Optional<Student> findFirstByRegistrationId(final String registrationId);
}
