package com.substring.irctc.service.impl;

import com.substring.irctc.config.AppConstants;
import com.substring.irctc.dto.FeedbackDto;
import com.substring.irctc.dto.PagedResponse;
import com.substring.irctc.entity.Feedback;
import com.substring.irctc.entity.User;
import com.substring.irctc.exceptions.ResourceNotFoundException;


import com.substring.irctc.repositories.FeedbackRepo;
import com.substring.irctc.repositories.UserRepo;
import com.substring.irctc.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public FeedbackDto submitFeedback(FeedbackDto feedbackDto, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new com.substring.irctc.exceptions.ResourceNotFoundException("User not found with id: " + userId));

        Feedback feedback = modelMapper.map(feedbackDto, Feedback.class);
        feedback.setUser(user);
        feedback.setCreatedAt(LocalDateTime.now());
        Feedback savedFeedback = feedbackRepo.save(feedback);

        return toDto(savedFeedback);
    }

    @Override
    public PagedResponse<FeedbackDto> getAllFeedbacks(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Feedback> feedbacks = feedbackRepo.findByIsArchivedFalse(pageable);

        return getPagedResponse(feedbacks);
    }

    @Override
    public PagedResponse<FeedbackDto> getUserFeedbacks(Long userId, int page, int size, String sortBy, String sortDir) {
        if (!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Feedback> feedbacks = feedbackRepo.findByUserIdAndIsArchivedFalse(userId, pageable);

        return getPagedResponse(feedbacks);
    }

    @Override
    public FeedbackDto getFeedbackById(Long id) {
        Feedback feedback = feedbackRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));

        if (feedback.isArchived()) {
            throw new ResourceNotFoundException("Feedback not found with id: " + id);
        }

        return toDto(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        Feedback feedback = feedbackRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));


        feedback.setArchived(true);
        feedbackRepo.save(feedback);
    }

    @Override
    public FeedbackDto updateFeedback(FeedbackDto feedbackDto, Long id, Long userId) {
        Feedback feedback = feedbackRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));

        if (!feedback.getUser().getId().equals(userId)) {
            throw new BadCredentialsException("You are not authorized to update this feedback");
        }

        feedback.setTitle(feedbackDto.getTitle());
        feedback.setDescription(feedbackDto.getDescription());
        feedback.setRating(feedbackDto.getRating());

        Feedback updatedFeedback = feedbackRepo.save(feedback);
        return toDto(updatedFeedback);
    }

    private FeedbackDto toDto(Feedback feedback) {
        FeedbackDto dto = modelMapper.map(feedback, FeedbackDto.class);
        dto.setUserId(feedback.getUser().getId());
        dto.setUserName(feedback.getUser().getName());
        return dto;
    }

    private PagedResponse<FeedbackDto> getPagedResponse(Page<Feedback> feedbacks) {
        List<FeedbackDto> content = feedbacks.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                content,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isLast(),
                feedbacks.isFirst()
        );
    }
}
