package com.substring.irctc.service;

import com.substring.irctc.config.AppConstants;
import com.substring.irctc.dto.TrainImageDataWithResource;
import com.substring.irctc.dto.TrainImageResponse;
import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainImage;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.TrainImageRepository;
import com.substring.irctc.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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

    public TrainImageResponse upload(MultipartFile file, Long trainNo) throws IOException {


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

        return TrainImageResponse.from(savedTrain.getTrainImage(), AppConstants.BASE_URL,trainNo);


    }


    public TrainImageDataWithResource loadImageByTrainNo(Long trainId) throws MalformedURLException {

        //get the train using tain no
        Train train = trainRepository.findById(trainId).orElseThrow(() -> new ResourceNotFoundException("Train not found!!"));
        TrainImage trainImage = train.getTrainImage();
        if (trainImage == null) {
            throw new ResourceNotFoundException("Image not found !!");
        }

        Path path = Paths.get(trainImage.getFileName());

        if(!Files.exists(path)){
            throw  new ResourceNotFoundException("Image not found !!");
        }

        UrlResource urlResource = new UrlResource(path.toUri());

        TrainImageDataWithResource trainImageDataWithResource = new TrainImageDataWithResource(trainImage, urlResource);


        return  trainImageDataWithResource;

    }
}
