package kz.itstep.studentmvc.controller;

import kz.itstep.studentmvc.repo.StudentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    private final StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students", studentRepo.findAll());

        return "student_list";
    }
}
