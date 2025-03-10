package com.example.Students.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {


    @Query("SELECT s  FROM Student  s WHERE s.email  =?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s  FROM Student  s WHERE s.id  =?1")
    Optional<Student> findStudentByUUID(UUID studentId);
}
