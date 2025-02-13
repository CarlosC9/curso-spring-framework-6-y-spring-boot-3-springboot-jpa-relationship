package com.carlos.curso.springboot.jpa.springbootjparelationship.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String lastname;

  @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JoinTable(
    name = "students_courses",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private List<Course> courses = new ArrayList<>();

  public Student() {
  }

  public Student(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public Student addCourse(Course course) {
    this.courses.add(course);
    return this;
  }

  @Override
  public String toString() {
    return "Student{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", lastname='" + lastname + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(id, student.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
