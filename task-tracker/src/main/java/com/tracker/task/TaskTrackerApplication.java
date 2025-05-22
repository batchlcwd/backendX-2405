package com.tracker.task;

import com.tracker.task.entities.*;
import com.tracker.task.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TaskTrackerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }


    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CertificateRepo certificateRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ProducdRepo producdRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private  StudentDao studentDao;

    @Override
    public void run(String... args) throws Exception {



        studentDao.getJoinedData();



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


//        Department department = departmentRepo.findById(1).get();
//        Student student1 = new Student();
//        student1.setName("kusha");
//        student1.setEmail("kusah@gmail.com");
//        student1.setDepartment(department);
//        Student student2 = new Student();
//        student2.setName("shruti");
//        student2.setEmail("shruti@gmail.com");
//        student2.setDepartment(department);
//        List<Student> studentList = new ArrayList<>();
//        studentList.add(student2);
//        studentList.add(student1);
//        department.setStudents(studentList);
//        Department savedDepartment = departmentRepo.save(department);
//        System.out.println("departement created:");


//        Category category1 = new Category();
//        category1.setTitle("Trending");
//        Category category2 = new Category();
//        category2.setTitle("Mobile Phones");
//
//
//        Product product1 = new Product();
//        product1.setName("Iphone 13");
//
//        Product product2 = new Product();
//        product2.setName("Asus ROG 123");


        //mapping
//        product1.getCategories().add(category1);
//        category1.getProducts().add(product1);

//        product1.addCategory(category1);
//        product1.addCategory(category2);
//        product2.addCategory(category1);

//        product1.getCategories().add(category2);
//        category2.getProducts().add(product1);
//
//        product2.getCategories().add(category1);
//        category1.getProducts().add(product2);


        //  save

//        producdRepo.save(product1);
//        producdRepo.save(product2);
    }
}
