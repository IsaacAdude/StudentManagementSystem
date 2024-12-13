package org.isaacadude.sms.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // id is an auto generated primary key
    private Integer id;
    private String name;
    private String instructor;
    @OneToMany(fetch = FetchType.EAGER) // this one will allow each course to have multiple students
    private Set<Student> students = new HashSet<>(); // Represents students registered for the course

    public Course() {}

    public Course( String name, String instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + "', instructor='" + instructor + "'}";
    }
}
