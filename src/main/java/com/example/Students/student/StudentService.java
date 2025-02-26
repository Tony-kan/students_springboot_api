package com.example.Students.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentService {

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final StudentRepository studentRepository;



    public List<Student> getStudentsService() {
            return studentRepository.findAll();
    }

    public void registerNewStudent(Student student) {
        log.info("Registering new student ...... : ${student}");
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            log.info("Student already registered ...... : ${student}");
            throw new IllegalStateException("Email already registered");
        }
         studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        log.info("Deleting student with id...... : ${id}");

        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id : "+id+" not found");
        }
        studentRepository.deleteById(id);

    }
}
