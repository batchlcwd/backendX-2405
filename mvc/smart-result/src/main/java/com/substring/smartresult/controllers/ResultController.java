package com.substring.smartresult.controllers;

import com.substring.smartresult.entities.Mark;
import com.substring.smartresult.entities.Student;
import com.substring.smartresult.payload.StudentForm;
import com.substring.smartresult.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@Controller
@RequestMapping("/results")
public class ResultController {


    @Autowired
    private ResultService resultService;

    // roll number
    @RequestMapping("/input")
    public String viewResult() {
        System.out.println("Results page");
        return "view_result";
    }


    @RequestMapping("/view")
    public String getResult() {
        // Logic to fetch and return the result
        return "result_data"; // Placeholder for actual result data
    }


    //add result ka form show kar rha hai.
    @RequestMapping("/add")
    public String addResult(Model model) {
        // Logic to fetch and return the result

        StudentForm studentForm = new StudentForm();

        model.addAttribute("studentForm", studentForm);

        return "add_result"; // Placeholder for actual result data
    }


    @RequestMapping(value = "/process-form", method = RequestMethod.POST)
    public String saveResult(
            @ModelAttribute StudentForm studentForm,
            Model model
    ) {

//        System.out.println(studentForm.getName());
//        System.out.println(studentForm.getRollNumber());
//        System.out.println(studentForm.getEmail());
//
//        System.out.println(studentForm.getMarks().size());
//
//        studentForm.getMarks().forEach(marks->{
//            System.out.println("Subject: " + marks.getSubject());
//            System.out.println("Marks: " + marks.getMarks());
//            System.out.println("Max Marks: " + marks.getMaxMarks());
//            System.out.println("Remark: " + marks.getRemark());
//            System.out.println("Grade: " + marks.getGrade());
//            System.out.println("-------------------------");
//        });

        String studentid = resultService.save(studentForm);

        model.addAttribute("studentId", studentid);

        return "result_success"; // Placeholder for actual result data
    }


//    view result controller method

    //@RequestMapping(value = "/view-result", method = RequestMethod.POST)
    @PostMapping("/view-result")
    public String viewResultByRollNumber(
            @RequestParam("rollNumber") String rollNumber, Model model) {
        System.out.println("Roll Number: " + rollNumber);

        Student student = resultService.getResultByRollNumber(rollNumber);
//        System.out.println(student.getName());
//        System.out.println(student.getMarkList().size());
        if (student == null) {
            return "result_not_found"; // Placeholder for result not found
        } else {

            double totalMarks = 0;
            double totalMaxMarks = 0;

            for (Mark mark : student.getMarkList()) {
                totalMarks += mark.getMarks();
                totalMaxMarks += mark.getMaxMarks();
            }

            double percentage = (totalMarks / totalMaxMarks) * 100;
//            model.addAttribute("percentage", String.format("%.2f",percentage)+"");

            DecimalFormat df = new DecimalFormat("#.00");
            model.addAttribute("percentage", df.format(percentage));
            model.addAttribute("totalMarks", Double.valueOf(totalMarks).intValue());
            model.addAttribute("totalMaxMarks", Double.valueOf(totalMaxMarks).intValue());

            String totalGrade = "F";

            if (percentage > 90) {
                totalGrade = "A+";
            } else if (percentage > 80 && percentage <= 90) {
                totalGrade = "A";
            } else if (percentage > 70 && percentage <= 80) {
                totalGrade = "B+";
            } else if (percentage > 60 && percentage <= 70) {
                totalGrade = "B";
            } else {
                totalGrade = "F";
            }

            model.addAttribute("totalGrade", totalGrade);


            model.addAttribute("student", student);
            model.addAttribute("marks", student.getMarkList());
            return "result_data";
        }


    }


}
