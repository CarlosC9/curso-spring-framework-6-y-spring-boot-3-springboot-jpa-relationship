package com.carlos.curso.springboot.jpa.springbootjparelationship.repositories;

import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
