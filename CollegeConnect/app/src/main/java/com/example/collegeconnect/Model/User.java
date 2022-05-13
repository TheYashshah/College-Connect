package com.example.collegeconnect.Model;

public class User {
    private String name;
    private String fullName;
    private String email;
    private String phoneno;
    private String department;
    private String enrollment;

    public User() {
    }

    public User(String name, String email, String fullName, String phoneno, String department, String enrollment) {
        this.name = name;
        this.email = email;
        this.fullName = fullName;
        this.phoneno = phoneno;
        this.department = department;
        this.enrollment= enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public String getphoneno() {
        return phoneno;
    }

    public void setphoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getdepartment() {
        return department;
    }

    public void setdepartment(String department) {
        this.department = department;
    }

    public String getenrollment() {
        return enrollment;
    }

    public void setId(String enrollment) {
        this.enrollment = enrollment;
    }
}
