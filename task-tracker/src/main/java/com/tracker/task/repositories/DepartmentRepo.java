package com.tracker.task.repositories;

import com.tracker.task.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {
}
