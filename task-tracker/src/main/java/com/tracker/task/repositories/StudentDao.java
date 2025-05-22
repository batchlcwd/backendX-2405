package com.tracker.task.repositories;

import com.tracker.task.entities.Department;
import com.tracker.task.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {

    @Autowired
    private EntityManager entityManager;

    public void getStudents() {
        System.out.println("getting student");
        //implement criteria api for getting student


        //get criteria builder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //create criteria query
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        // defining root
        Root<Student> root = criteriaQuery.from(Student.class);
        // build conditions using Predicate-->  builder
        Predicate predicate = criteriaBuilder.equal(root.get("name"), "Sarthak");
        Predicate predicate1 = criteriaBuilder.equal(root.get("email"), "sarthak123@gmail.com");
        Predicate predicate2 = criteriaBuilder.greaterThan(root.get("id"), 3);
        Predicate andPredicate = criteriaBuilder.or(predicate1, predicate2);
        //build
        criteriaQuery.select(root).where(andPredicate);
        //select karenge data
        List<Student> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        resultList.forEach(student -> System.out.println(student.getName()));


    }

    public void getJoinedData() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);

        Root<Student> studentRoot = query.from(Student.class);


        studentRoot.join("department");


        query.select(studentRoot);

        List<Student> resultList = entityManager.createQuery(query).getResultList();

        resultList.forEach(student -> System.out.println(student.getName() + " : " + student.getDepartment().getName()));


    }

}
