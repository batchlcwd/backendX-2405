package com.substring.irctc.dto;

import com.substring.irctc.entity.TrainImage;

import java.time.LocalDateTime;

public record TrainImageResponse(
        Long id,
        String fileName,
        String fileType,
        String url,
        long size,
        LocalDateTime uploadTime
) {


    public static TrainImageResponse from(TrainImage image, String baseUrl,Long trainNo) {
        return new TrainImageResponse(
                image.getId(),
                image.getFileName(),
                image.getFileType(),
                baseUrl + "/trains/" + trainNo+"/image",
                image.getSize(),
                image.getUploadTime()
        );
    }

}
