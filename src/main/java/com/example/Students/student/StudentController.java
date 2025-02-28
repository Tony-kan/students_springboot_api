package com.example.Students.student;

import com.example.Students.Commons.ApiResponse;
import com.example.Students.student.dto.CreateStudentRequest;
import com.example.Students.student.hateoas.StudentModel;
import com.example.Students.student.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    public ResponseEntity<PagedModel<?>> getAllStudents( @RequestParam(value="page", defaultValue = "0") int page,
                                                         @RequestParam(value="size", defaultValue = "20") int size,
                                                        PagedResourcesAssembler<Student> pagedResourcesAssembler) {
     return studentService.getAllStudents(page,size,pagedResourcesAssembler);
    }

    @GetMapping("/me/{studentId}")
    public ResponseEntity<StudentModel> getStudentProfileDetails(@PathVariable UUID studentId) {
        return studentService.getStudentProfile(studentId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addNewStudent(@RequestBody CreateStudentRequest createStudentRequest) {
         return studentService.registerNewStudent(createStudentRequest);
    }


    @PutMapping(path="{studentId}")
    public ResponseEntity<ApiResponse> updateStudent(
            @PathVariable("studentId") UUID studentId,
            @RequestParam(required = false)  String fullName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate dob,
            @RequestParam(required = false) String gender) {

       return studentService.updateStudentDetails(studentId,fullName,email,dob,gender);
    }


    @DeleteMapping(path="{studentId}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("studentId") Long id) {
      return studentService.deleteStudent(id);
    }
}
