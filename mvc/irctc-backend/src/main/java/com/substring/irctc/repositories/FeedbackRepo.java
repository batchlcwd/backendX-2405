package com.substring.irctc.repositories;

import com.substring.irctc.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
    
    Page<Feedback> findByIsArchivedFalse(Pageable pageable);
    
    Page<Feedback> findByUserIdAndIsArchivedFalse(Long userId, Pageable pageable);
    
    boolean existsByIdAndUserId(Long id, Long userId);
}
