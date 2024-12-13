package org.isaacadude;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.isaacadude.sms.models.Student;

import static org.isaacadude.App.studentService;


/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    // this will  verify that a Student object can be created, saved, and retrieved correctly from the database using the studentService.

    public void testCreateStudent() {
        Student student = new Student("isaac@gmail.com", "isaac adude", "password");
        studentService.createStudent(student);

        Student fetchedStudent = studentService.getStudentByEmail("isaac@gmail.com");
        assertNotNull("student will be created and fetched", fetchedStudent );
        assertEquals("the email will match", "isaac@gmail.com", fetchedStudent.getEmail() );
    }
}

