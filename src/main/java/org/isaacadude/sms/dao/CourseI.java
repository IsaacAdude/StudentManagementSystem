package org.isaacadude.sms.dao;

import org.isaacadude.sms.models.Course;

import java.util.List;

/**
 * The CourseI interface declares abstract methods and
 * is implemented by other classes to provide services for a course.
 */
public interface CourseI {
    void createCourse(Course course);
    Course getCourseById(int courseId);

    boolean validateStudent(String email, String password);

    List<Course> getAllCourses();

}
