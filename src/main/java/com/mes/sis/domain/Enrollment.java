package com.mes.sis.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"classId", "student"})
@Entity(name = "enrollment")
@NoArgsConstructor
public class Enrollment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "class_id")
  @JsonBackReference
  private Class classId;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @Column(nullable = false)
  private Date createdAt;

  public Enrollment(Student student, Class classId) {
    this.classId = classId;
    this.student = student;
    this.createdAt = new Date();
  }
}
