package com.tracker.task.repositories;

import com.tracker.task.TaskTrackerApplication;
import com.tracker.task.entities.Task;
import com.tracker.task.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //save
    //findAll
    //findById
    //delete

    //    custom methods / custom finder methods
    List<Task> findByTitle(String title);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByCreatedAt(LocalDateTime localDateTime);

    List<Task> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Task> findByTitleAndStatus(String title, TaskStatus status);


    List<Task> findByTitleContainingIgnoreCase(String titleKeyword);


    //    query methods-- custom query
    @Query("SELECT t FROM Task t")
    List<Task> getAllTask1();


    @Query("SELECT t from Task t where t.id = ?1 and t.status = ?2")
    Task getById(long id, TaskStatus status);


    @NativeQuery("SELECT * FROM jpa_tasks")
    List<Task> getAllTask2();



}
