package com.tracker.task.repositories;

import com.tracker.task.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducdRepo extends JpaRepository<Product,Integer> {
}
