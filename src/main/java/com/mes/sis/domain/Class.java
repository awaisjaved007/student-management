package com.mes.sis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mes.sis.vo.request.ClassVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
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

  @Fetch(FetchMode.JOIN)
  @OneToMany(
      mappedBy = "classId",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<Enrollment> enrollments;

  @Column(nullable = false)
  private Date createdAt;

  public Class(ClassVO classVO) {
    this.name = classVO.getName();
    this.createdAt = new Date();
  }

  @Transient
  public List<Student> getStudents() {
    return this.enrollments.stream().map(x -> x.getStudent()).collect(Collectors.toList());
  }
}
