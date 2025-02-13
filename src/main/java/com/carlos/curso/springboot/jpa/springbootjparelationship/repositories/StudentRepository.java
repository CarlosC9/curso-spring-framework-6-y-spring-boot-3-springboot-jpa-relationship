package com.carlos.curso.springboot.jpa.springbootjparelationship.repositories;

import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
