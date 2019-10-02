package com.mes.sis.domain;

import com.mes.sis.vo.request.ClassVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(
    name = "classes",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "unq_class_name",
          columnNames = {"name"})
    })
public class Class {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String section;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<Enrollment> enrollments;

  public Class(ClassVO classVO){
    this.name = classVO.getName();
    this.section = classVO.getSection();
  }

}
