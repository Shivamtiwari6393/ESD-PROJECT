package com.esd.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esd.project.StudentRepository;
import com.esd.project.entities.Student;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ALL_STUDENTS----------------------------------------------------------

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // FIND STUDENT BY ID-----------------------------------------------

    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    // CREATE
    // STUDENT-------------------------------------------------------------------

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // UPDATE STUDENT-----------------------------------------------------------

    public Student updateStudent(Long studentId, Student updatedStudent) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);

        if (existingStudent.isPresent()) {
            updatedStudent.setStudentId(existingStudent.get().getStudentId());
            return studentRepository.save(updatedStudent);
        } else {
            throw new IllegalArgumentException("Student not found");
        }
    }

    // DELETE STUDENT----------------------------------------------------

    public int deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            if (student.getStatus() != 0) {
                student.setStatus(0);
                studentRepository.save(student);
                // if user is there and deleted then return 1
                return 1;

            } else {
                // if user is already deleted then return 0
                return 0;
            }

        } else {

            // if user is not in database then return 2
            return 2;
        }

    }

    // FIND BY STATUS --------------------------------------

    public List<Student> getStudentsByStatus(int status) {
        List<Student> student = studentRepository.findByStatus(status);
        return student;
    }
}

// ***************************************************************************************