package org.isaacadude.sms.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.isaacadude.sms.dao.CourseI;
import org.isaacadude.sms.models.Course;
import org.isaacadude.sms.models.Student;
import org.isaacadude.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseService is a concrete class. This class implements the
 * CourseI interface, overrides all abstract service methods and
 * provides implementation for each method.
 */

/*
Each method uses Hibernate’s SessionFactory to interact with the database through a Session,
with each method handling session opening, closing, and transaction management
 */
public class CourseService implements CourseI {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void createCourse(Course course) {
        try (Session session = sessionFactory.openSession()) {
            session.save(course);
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, courseId);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Course", Course.class).list();
        }
    }

    @Override
    public boolean validateStudent(String email, String password) {
        return false;
    }
}
