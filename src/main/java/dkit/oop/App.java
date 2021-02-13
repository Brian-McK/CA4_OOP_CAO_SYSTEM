package dkit.oop;

import java.util.List;

/*
* Brian McKenna
* SD2B
*/

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    private void start() {

        // load students
        StudentManager studentManager = new StudentManager();

        Student s1 = new Student(1234,"05/12/1991","hello","myemail@email.ie");
        Student s2 = new Student(s1);
        s2.setCaoNumber(22222);

        Student s3 = new Student(9999,"12/11/2001","yeaBoi","mygmail@email.ie");


        // load courses
        CourseManager courseManager= new CourseManager();

        System.out.println(courseManager.getAllCourses());

        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        //
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);

        // display a menu to do things
        // manual testing of mgr public interface

//        if ( mgr.login(22224444, "xxxx","bbbb") )
//        {
//            Student student = mgr.getStudentDetails(22224444);
//
//            System.out.println("Student: " + student);
//        }
//        else
//            System.out.println("Not logged in - try again");


        //mgr.saveToFile();

    }
}

// TODO: 13/02/2021 - Create methods for adding data from a file
