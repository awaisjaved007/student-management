package com.mes.sis.repository;

import com.mes.sis.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
  Optional<Enrollment> findFirstByClassId_IdAndStudent_Cnic(final Long classId, final String cnic);
}
