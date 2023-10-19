package com.esd.project.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esd.project.entities.Course;
import com.esd.project.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // 1.ALL USER ---------------------------------------------

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // 2.GET COURSE BY ID -------------------------------------

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    // 3.CREATE COURSE---------------------------------------

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // 4.UPDATE COURSE------------------------------------------------

    public Course updateCourse(Long courseId, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse != null) {
            existingCourse.setCourseId(courseId);
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setCourseDescription(updatedCourse.getCourseDescription());
            // Save the updated course
            System.out.println("updated course successfully");
            return courseRepository.save(existingCourse);
        } else {
            System.out.println("course not found");
            throw new IllegalArgumentException("Course not found");
        }
    }

    // 5.DELETE COURSE--------------------------------------------------

    public int deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            if (course.getStatus() != 0) {
                course.setStatus(0);
                courseRepository.save(course);
                // if course is there and deleted then return 1
                return 1;

            } else {
                // if course is already deleted then return 0
                return 0;
            }

        } else {

            // if course is not in database then return 2
            return 2;
        }
    }

    // 6.FIND BY STATUS -------------------------------------------------

    public List<Course> getCoursesByStatus(int status) {
        List<Course> courses = courseRepository.findByStatus(status);
        return courses;
    }
}
// ***********************************************************************************
