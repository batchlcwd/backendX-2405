package com.tracker.task.repositories;

import com.tracker.task.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepo extends JpaRepository<Certificate , Integer> {
}
