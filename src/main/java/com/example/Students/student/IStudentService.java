package com.example.Students.student;

import com.example.Students.Commons.ApiResponse;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IStudentService {
    ResponseEntity<PagedModel<?>> getAllStudents(int page, int size, PagedResourcesAssembler<Student>  studentPagedResourcesAssembler);
    ResponseEntity<ApiResponse> registerNewStudent(Student student);
    ResponseEntity<ApiResponse> updateStudentDetails(UUID studentId,Student student);
    ResponseEntity<ApiResponse> getStudentProfile(String studentId);
    ResponseEntity<ApiResponse> deleteStudent(Long id);
    ResponseEntity<Student> getStudentById(Long id);

}
