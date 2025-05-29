package com.substring.smartresult.controllers;

import com.substring.smartresult.entities.Student;
import com.substring.smartresult.payload.StudentForm;
import com.substring.smartresult.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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




}
