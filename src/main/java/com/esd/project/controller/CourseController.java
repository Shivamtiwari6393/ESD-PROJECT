package com.esd.project.controller;

import java.util.List;

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

import com.esd.project.entities.Course;
import com.esd.project.services.CourseService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 1. ALL_COURSE-------------------------------------------------------------

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // 2.GET COURSE BY ID--------------------------------------------------
    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    // 3.CREATE COURSE-------------------------------------------------------------

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // 4.UPDATE COURSE-----------------------------------------------------

    @PutMapping("/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        return courseService.updateCourse(courseId, updatedCourse);
    }

    // 5.DELETE COURSE-----------------------------------------------------

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        String message;

        if (courseService.deleteCourse(courseId) == 1) {
            message = "Course with ID " + courseId + " has been deleted successfully.";
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } else if (courseService.deleteCourse(courseId) == 0) {
            message = "Course with ID " + courseId + " already deleted.";
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        } else {
            message = "Course with ID " + courseId + " not found.";
            return ((BodyBuilder) ResponseEntity.notFound()).body(message);
        }
    }

    // 6.GET COURSES BY STATUS---------------------------------------------

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getCoursesByStatus(@PathVariable int status) {
        List<Course> course = courseService.getCoursesByStatus(status);

        if (course.isEmpty()) {

            String message = "No course with status " + status + " found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
    }
}
