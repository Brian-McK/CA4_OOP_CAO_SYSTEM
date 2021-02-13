package dkit.oop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test1()
    {
        Student s1 = new Student(1234,"05/12/1991","hello","myemail@email.ie");
        Student s2 = new Student(s1);

        // check that the references are not equal to each other
        // check if the values are the same i.e clone

        assert(s1 != s2 && s1.equals(s2));
    }

    @Test
    public void test2()
    {
        StudentManager studManager = new StudentManager();
        Student s1 = new Student(1234,"05/12/1991","hello","myemail@email.ie");

        Student cp = studManager.getStudent(1234);

        assert(s1 != cp && s1.equals(cp));
    }
}
