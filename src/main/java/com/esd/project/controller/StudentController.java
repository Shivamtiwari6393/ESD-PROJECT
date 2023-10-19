package com.esd.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esd.project.entities.Student;
import com.esd.project.services.StudentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. GET ALL STUDENTS ---------------------------------------------------

    @GetMapping
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }

    // 2. GET STUDENT BY ID--------------------------------------------------------

    @GetMapping("/{studentId}")
    public Optional<Student> getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    // 3. CREATE STUDENT------------------------------------------------------------

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // 4.UPDATE STUDENT-------------------------------------------------------------
    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(studentId, updatedStudent);
    }

    // 5.DELETE_STUDENT--------------------------------------------------------------

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        String message;

        if (studentService.deleteStudent(studentId) == 1) {
            message = "User with ID " + studentId + " has been deleted successfully.";
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } else if (studentService.deleteStudent(studentId) == 0) {
            message = "User with ID " + studentId + " already deleted.";
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        } else {
            message = "User with ID " + studentId + " not found.";
            return ((BodyBuilder) ResponseEntity.notFound()).body(message);
        }

    }

    // 6. GET STUDENT BY STATUS------------------------------------

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getStudentsByStatus(@PathVariable int status) {
        List<Student> students = studentService.getStudentsByStatus(status);

        if (students.isEmpty()) {
            // If no users are found, return a custom message
            String message = "No users with status " + status + " found.";
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            // If users are found, return the list of users
            System.out.println("return the student by status");
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }

}
