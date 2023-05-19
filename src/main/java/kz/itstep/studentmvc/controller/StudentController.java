package kz.itstep.studentmvc.controller;

import kz.itstep.studentmvc.model.Student;
import kz.itstep.studentmvc.repo.StudentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/students/new")
    public String addNewStudent(Model model){
        model.addAttribute("student", new Student());
        return "student_new";
    }
    @GetMapping("/students/{id}")
    public String showStudentWithId(@PathVariable Long id, Model model){
        model.addAttribute("student", studentRepo.findById(id).orElseThrow(()->new RuntimeException("no student with id")));

        return "student_show";
    }

    @PostMapping("/students/new")
    public String addNewStudent(@ModelAttribute Student student){
        if(student!=null){
            studentRepo.save(student);
        }
        return "redirect:/students";
    }
}
