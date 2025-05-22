package com.tracker.task.services;

import com.tracker.task.entities.Task;
import com.tracker.task.entities.TaskStatus;
import com.tracker.task.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;


    // saveTask
    @Transactional
    public Task saveTask(Task task) {


        // logics likhenge
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setCreatedAt(task.getCreatedAt().minusDays(4));

        // save karene ka logic likhenge
        Task savedTask = taskRepository.save(task);

//        ....... 15 task
        System.out.println("Task saved with ID: " + savedTask.getId());


        return savedTask;
    }

    //update task

    // get all task

    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }


    // get task by id

    public Task getById(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return task;
    }


    // delete task by id
    public void deleteById(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    // get all task by status

    public List<Task> getByStatus(TaskStatus status) {


        List<Task> byStatus = taskRepository.findByStatus(status);
        return byStatus;
    }


    // get all task by date

    public List<Task> getByCreatedDate(LocalDateTime createdAt) {


        List<Task> byCreatedAt = taskRepository.findByCreatedAt(createdAt);
        return byCreatedAt;

    }


    //search tasks
    public List<Task> searchByTitle(String titleKeyword) {
        List<Task> tasks = taskRepository.findByTitleContainingIgnoreCase(titleKeyword);
        return tasks;
    }

    public List<Task> getByCreatedDateBetween(LocalDateTime start,LocalDateTime end){
        List<Task> byCreatedAt = taskRepository.findByCreatedAtBetween(start,end);
        return byCreatedAt;
    }


}
