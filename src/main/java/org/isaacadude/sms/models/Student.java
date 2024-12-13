package org.isaacadude.sms.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
/*
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bi-directional relationship.
 * Implement Lombok annotations to eliminate boilerplate code.
 */

public class Student {
    @Id
    private String email;
    private String name;
    private String password;

    @OneToMany(fetch = FetchType.EAGER) // this maps the relationship with Course meaning that a student can be registerd in multiple courses
    private Set<Course> courses = new HashSet<Course>(); // represents the courses a student is registered in

    public Student() {}

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

}