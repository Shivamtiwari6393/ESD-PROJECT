package com.esd.project;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class User {
    // ID-----------ID-------------------ID-------------------------------------ID-------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    // USERNAME-------------- USERNAME--------- USERNAME---------- USERNAME-----
    // USERNAME---------

    @Column(nullable = false, unique = true)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // PASSWORD------------PASSWORD-------------------PASSWORD-------------------PASSWORD----------PASSWORD-----

    @Column(nullable = false)
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public CharSequence getPassword() {
        return password;
    }

    // CONFIRMPASSWORD---------CONFIRMPASSWORD------------CONFIRMPASSWORD------CONFIRMPASSWORD---------CONFIRMPASSWORD-----------

    @Transient
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // USER_CREATED_AT----------USER_CREATED_AT--------------USER_CREATED_AT-------------USER_CREATED_AT-----------USER_CREATED_AT---------

    @CreationTimestamp
    @Column(name = "user_created_at")
    private LocalDateTime userCreatedAt;

    // STATUS---------STATUS--------------STATUS-----------STATUS-------------STATUS-----------------STATUS----------STATUS---------
    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private int status = 1;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
// -----------------------------------------------------------------------------------------------------------------------------------