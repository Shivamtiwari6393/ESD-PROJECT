package com.esd.project.entities;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

    // COURSE
    // ID---------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    public Long getCourseId() {
        return courseId;
    }

    // COURSE
    // NAME-------------------------------------------------------------------

    @Column(name = "course_name")
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // COURSE
    // DESCRIPTION----------------------------------------------------------------

    @Column(name = "course_description")
    private String courseDescription;

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    // STATUS--------------------------------------------------------

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private int status = 1;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCourseId(Long courseId2) {
    }

    // CREATED AT-----------------------------------------------------------------
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    // UPDATED AT-------------------------------------------------------------------
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
// ***********************************************************************************************