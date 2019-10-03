package com.mes.sis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mes.sis.vo.request.ClassVO;
import com.mes.sis.vo.request.StudentVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(of = "registrationId")
@Entity
@NoArgsConstructor
@Table(
    name = "student",
    uniqueConstraints = {
      @UniqueConstraint(name = "unq_registrationId", columnNames = "registrationId")
    })
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 36, updatable = false)
  @Size(max = 36, message = "Registration id length is invalid.")
  private String registrationId;

  @Column(nullable = false, length = 20)
  private String firstName;

  @Column(nullable = false, length = 20)
  private String lastName;

  // This will be unique for every student
  @Size(max = 20, message = "Registration id length is invalid.")
  @Column(unique = true, nullable = false, length = 20)
  private String cnic;

  @OneToMany(
      mappedBy = "student",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JsonIgnore
  private Set<Enrollment> enrollments;

  @Column(nullable = false)
  private Date createdAt;

  @PrePersist
  public void onCreate() {
    this.registrationId = UUID.randomUUID().toString();
    this.createdAt = new Date();
  }

  public Student(StudentVO studentVO) {
    this.firstName = studentVO.getFirstName();
    this.lastName = studentVO.getLastName();
    this.cnic = studentVO.getCnic();
  }

  @JsonIgnore
  public Set<Enrollment> getEnrollments() {
    if (this.enrollments == null) this.enrollments = new HashSet<>();
    return this.enrollments;
  }

  @Transient
  public List<ClassVO> getClasses() {
    return this.enrollments.stream()
        .map(x -> new ClassVO(x.getClassId().getName()))
        .collect(Collectors.toList());
  }
}
