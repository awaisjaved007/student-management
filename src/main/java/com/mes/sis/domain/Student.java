package com.mes.sis.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "registrationId")
@Entity
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
  @Size(max = 35, message = "Registration id length is invalid.")
  private String registrationId;

  @Column(nullable = false, length = 20)
  private String firstName;

  @Column(nullable = false, length = 20)
  private String lastName;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<Enrollment> enrollments;

  @PrePersist
  public void onCreate() {
    this.registrationId = UUID.randomUUID().toString();
  }
}
