package com.substring.irctc.dto;

import com.substring.irctc.entity.TrainImage;
import org.springframework.core.io.Resource;

public record TrainImageDataWithResource(
        TrainImage trainImage,
        Resource resource
) {
}
