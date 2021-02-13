package dkit.oop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void StudentCloneTest()
    {
        // check that the references are not equal but the values are the same
        Student s1 = new Student(1234,"05/12/1991","hello","myemail@email.ie");
        Student s2 = new Student(s1);

        assert(s1 != s2 && s1.equals(s2));
    }

    @Test
    public void StudentCloneFromStudentManagerTest()
    {
        StudentManager studManager = new StudentManager();
        Student s1 = new Student(1234,"05/12/1991","hello","student1@email.ie");
        Student copy = studManager.getStudent(1234);

        assert(s1 != copy && s1.equals(copy));
    }
}
