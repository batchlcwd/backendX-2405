package com.substring.irctc.service;

import com.substring.irctc.dto.FeedbackDto;
import com.substring.irctc.dto.PagedResponse;

public interface FeedbackService {
    
    FeedbackDto submitFeedback(FeedbackDto feedbackDto, Long userId);
    
    PagedResponse<FeedbackDto> getAllFeedbacks(int page, int size, String sortBy, String sortDir);
    
    PagedResponse<FeedbackDto> getUserFeedbacks(Long userId, int page, int size, String sortBy, String sortDir);
    
    FeedbackDto getFeedbackById(Long id);
    
    void deleteFeedback(Long id);
    
    FeedbackDto updateFeedback(FeedbackDto feedbackDto, Long id, Long userId);
}
