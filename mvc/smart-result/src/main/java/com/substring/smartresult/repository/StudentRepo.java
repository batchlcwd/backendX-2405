package com.substring.smartresult.repository;

import com.substring.smartresult.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,String> {

    Student findByRollNumber(String rollNumber);
}
