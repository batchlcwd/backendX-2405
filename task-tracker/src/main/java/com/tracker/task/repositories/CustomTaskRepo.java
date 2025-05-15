package com.tracker.task.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTaskRepo {


    @Autowired
    private EntityManager entityManager;




}
