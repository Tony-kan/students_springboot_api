package com.example.Students.student;

import com.example.Students.student.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/students")
@Slf4j
public class StudentController {
    private final StudentServiceImpl studentService;


    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
     return studentService.getStudentsService();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student) {
         studentService.registerNewStudent(student);
    }


    @PutMapping(path="{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false)  String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate dob) {

        studentService.updateStudentDetails(studentId,name,email,dob);
    }


    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
       studentService.deleteStudent(id);
    }
}
