package com.substring.quiz.functions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizDto {

    private  String id;
    private  String title;
    private  String description;
    private  Integer maxMarks;
    private  Integer timeLimit;
    private  String createdBy;
    private  Integer noOfQuestions;
    private  String imageUrl;
    private  Boolean live;
    private  Integer passingMarks;
    private  String categoryId;
    private  CategoryDto categoryDto;
}
