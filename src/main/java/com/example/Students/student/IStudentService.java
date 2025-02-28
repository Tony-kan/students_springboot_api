package com.example.Students.student;

import com.example.Students.Commons.ApiResponse;
import com.example.Students.student.dto.CreateStudentRequest;
import com.example.Students.student.hateoas.StudentModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

public interface IStudentService {
    ResponseEntity<PagedModel<?>> getAllStudents(int page, int size, PagedResourcesAssembler<Student>  studentPagedResourcesAssembler);
    ResponseEntity<ApiResponse> registerNewStudent(CreateStudentRequest createStudentRequest);
    ResponseEntity<ApiResponse> updateStudentDetails(UUID studentId, String fullName, String email, LocalDate dob,String gender);
//    ResponseEntity<ApiResponse> getStudentProfile(String studentId);
    ResponseEntity<ApiResponse> deleteStudent(Long id);
    ResponseEntity<StudentModel> getStudentProfile(UUID studentId);
    ResponseEntity<StudentModel> getStudentById(UUID studentId);

}
