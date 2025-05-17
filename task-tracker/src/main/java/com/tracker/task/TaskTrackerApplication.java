package com.tracker.task;

import com.tracker.task.entities.Certificate;
import com.tracker.task.entities.Student;
import com.tracker.task.repositories.CertificateRepo;
import com.tracker.task.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TaskTrackerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }


    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CertificateRepo certificateRepo;

    @Override
    public void run(String... args) throws Exception {


// going to save student and certificate

//        Student student = new Student();
//        student.setEmail("sarthak@gmail.com");
//        student.setName("Sarthak");
//
//
//        Certificate certificate = new Certificate();
//        certificate.setTitle("Java Certification");
//        certificate.setDescription("This is java certification");
//        certificate.setIssuedAt(LocalDate.now());
//
//
//        Student savedStudent = studentRepo.save(student);
//        System.out.println("student saved success");
//
//
//        savedStudent.setCertificate(certificate);
//        certificate.setStudent(savedStudent);
//
//        certificateRepo.save(certificate);
//        System.out.println("certificate also saved");

//        Student student = studentRepo.findById(3).get();
//        System.out.println(student.getName());
//
//
//        Certificate certificate = student.getCertificate();
//        System.out.println(certificate.getTitle());

//        Certificate certificate = certificateRepo.findById(1).get();
//        System.out.println(certificate.getTitle());
//
//        Student student = certificate.getStudent();
//        System.out.println(student.getName());


    }
}
