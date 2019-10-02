package com.mes.sis.repository;

import com.mes.sis.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {
  Optional<Class> findFirstByName(final String name);

  Optional<Class> findFirstById(final Long id);
}
