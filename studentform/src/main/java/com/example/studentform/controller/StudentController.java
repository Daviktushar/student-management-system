package com.example.studentform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.studentform.model.Student;
import com.example.studentform.repository.StudentRepository;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository repo;

    // Form page open
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    // Save student data with duplicate check
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student, Model model) {

        // check duplicate email
        if(repo.existsByEmail(student.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            model.addAttribute("student", new Student());
            return "form";
        }

        repo.save(student);

        return "redirect:/students";
    }

    // Show students list
    @GetMapping("/students")
    public String viewStudents(Model model) {

        model.addAttribute("students", repo.findAll());

        return "students";
    }
}