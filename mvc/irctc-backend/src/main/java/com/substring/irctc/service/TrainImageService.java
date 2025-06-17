package com.substring.irctc.service;

import com.substring.irctc.dto.TrainImageResponse;
import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainImage;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.TrainImageRepository;
import com.substring.irctc.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class TrainImageService {


    @Value("${train.image.folder.path}")
    private String folderPath;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TrainImageRepository trainImageRepository;

    public TrainImageResponse upload(MultipartFile file, String trainNo) throws IOException {


        Train train = trainRepository.findById(trainNo).orElseThrow(() -> new ResourceNotFoundException("train not found !!"));


        //checking and creating folder.
        if (!Files.exists(Paths.get(folderPath))) {
            System.out.println("creating folder");
            Files.createDirectories(Paths.get(folderPath));
        }
        String fullFilepath = folderPath + UUID.randomUUID() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(fullFilepath), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("file uploaded");
        TrainImage trainImage = new TrainImage();
        trainImage.setFileName(fullFilepath);
        trainImage.setFileType(file.getContentType());
        trainImage.setSize(file.getSize());

        trainImage.setTrain(train);
        train.setTrainImage(trainImage);

        Train savedTrain = trainRepository.save(train);

        return TrainImageResponse.from(savedTrain.getTrainImage(), "https://localhost:8080");


    }

}
