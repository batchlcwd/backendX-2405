package com.substring.smartresult.service;

import com.substring.smartresult.entities.Mark;
import com.substring.smartresult.entities.Student;
import com.substring.smartresult.payload.StudentForm;
import com.substring.smartresult.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultService {

    @Autowired
    private StudentRepo studentRepo;


    public String save(StudentForm studentForm){

        Student student=new Student();

        student.setId(UUID.randomUUID().toString());
        student.setName(studentForm.getName());
        student.setRollNumber(studentForm.getRollNumber());
        student.setEmail(studentForm.getEmail());
        student.setSchoolName(studentForm.getSchoolName());
        student.setDob(studentForm.getDob());
        student.setGender(studentForm.getGender());


        List<Mark> marksList = studentForm.getMarks().stream().map(markForm -> {
            Mark mark = new Mark();
            mark.setSubject(markForm.getSubject());
            mark.setMarks(markForm.getMarks());
            mark.setMaxMarks(markForm.getMaxMarks());
            mark.setRemark(markForm.getRemark());
            mark.setGrade(markForm.getGrade());
            mark.setStudent(student);
            return mark;

        }).toList();

        student.setMarkList(marksList);

        Student savedStudent = studentRepo.save(student);

        System.out.println("Student saved with ID: " + savedStudent.getId());

        return savedStudent.getId();


    }


    public Student getResultByRollNumber(String rollNumber) {
        return studentRepo.findByRollNumber(rollNumber);
    }


}
