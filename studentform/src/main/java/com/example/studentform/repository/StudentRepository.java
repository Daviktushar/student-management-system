package com.example.studentform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentform.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

}