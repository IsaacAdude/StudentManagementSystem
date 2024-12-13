package org.isaacadude.sms.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.isaacadude.sms.dao.StudentI;
import org.isaacadude.sms.models.Course;
import org.isaacadude.sms.models.Student;
import org.isaacadude.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentService is a concrete class. This class implements the
 * StudentI interface, overrides all abstract service methods and
 * provides implementation for each method.
 */
public class StudentService implements StudentI {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }

    @Override // this will create student
    public void createStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(student);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override // this will retrieve student by email
    public Student getStudentByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student WHERE email = :email", Student.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    @Override // to validate login credentials
    public boolean validateStudent(String email, String password) {
        Student student = getStudentByEmail(email);
        return student != null && student.getPassword().equals(password);
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {
        // this will retrieve Student and Course entities by email and courseId.
        Student student = getStudentByEmail(email);
        CourseService courseService = new CourseService();
        Course course = courseService.getCourseById(courseId);

        if (student != null && course != null) { // this will open a new session and start a transaction to persist the changes.
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            try { // this will add the course to the student's course list and the student to the course's student list
                // just to make sure that both sides of the relationship are updated.
                student.getCourses().add(course);
                course.getStudents().add(student);

                session.update(student);  // Persisting changes on student side as well
                session.update(course);   // and to ensure both entities reflect changes in the database

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    @Override
    public List<Course> getStudentCourses(String email) {
        Student student = getStudentByEmail(email);
        return student != null ? new ArrayList<>(student.getCourses()) : new ArrayList<>();
    }
}
