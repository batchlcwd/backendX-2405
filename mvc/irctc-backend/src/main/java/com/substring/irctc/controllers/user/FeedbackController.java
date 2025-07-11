package com.substring.irctc.controllers.user;

import com.substring.irctc.config.AppConstants;
import com.substring.irctc.dto.FeedbackDto;
import com.substring.irctc.dto.PagedResponse;

import com.substring.irctc.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> submitFeedback(
            @Valid @RequestBody FeedbackDto feedbackDto
    ) {

        FeedbackDto createdFeedback = feedbackService.submitFeedback(feedbackDto, feedbackDto.getUserId());
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PagedResponse<FeedbackDto> getAllFeedbacks(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir) {

        return feedbackService.getAllFeedbacks(page, size, sortBy, sortDir);
    }

    @GetMapping("/user/{userId}")
    public PagedResponse<FeedbackDto> getUserFeedbacks(
            @PathVariable Long userId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir) {

        return feedbackService.getUserFeedbacks(userId, page, size, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(
            @Valid @RequestBody FeedbackDto feedbackDto,
            @PathVariable Long id
    ) {

        FeedbackDto updatedFeedback = feedbackService.updateFeedback(feedbackDto, id, feedbackDto.getUserId());
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(
            @PathVariable Long id
    ) {

        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
//
//    @GetMapping("/user/{userId}")
//    public PagedResponse<FeedbackDto> getCurrentUserFeedbacks(
//            @PathVariable Long userId,
//            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE) int page,
//            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
//            @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir
//           ) {
//
//        return feedbackService.getUserFeedbacks(userId, page, size, sortBy, sortDir);
//    }
}
