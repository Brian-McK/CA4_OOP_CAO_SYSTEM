package dkit.oop;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);

        // load students
        StudentManager studentManager = new StudentManager();

//        System.out.println(studentManager);

        // load courses
        CourseManager courseManager= new CourseManager();

        System.out.println(courseManager);

        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        //
        CourseChoicesManager courseChoicesManager = new CourseChoicesManager(studentManager, courseManager);

        // display a menu to do things
        // manual testing of mgr public interface

        System.out.println("Welcome to the CAO");

        MenuOptions selectedOption = MenuOptions.CONTINUE;

        while (selectedOption != MenuOptions.QUIT)
        {
            try{
                // print the menu
                // get users selection
                // act on the selection
                // exit on quit
                printMenu();
                selectedOption = MenuOptions.values()[Integer.parseInt(scanner.nextLine().trim())];

                switch (selectedOption)
                {
                    case DISPLAY_COURSE:
//                        displayCourse();
                    case DISPLAY_ALL_COURSES:
//                        displayAllCourses();
                    case DISPLAY_CURRENT_CHOICES:
//                        displayCurrentCourseChoices();
                    case UPDATE_CHOICES:
//                        updateCourseChoices();
                        break;
                    case QUIT:
                        break;
                    default:
                        System.out.println("Invalid entry, try again");
                }
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Invalid entry, try again");
            }
        }

//        if ( mgr.login(22224444, "xxxx","bbbb"))
//        {
//            Student student = mgr.getStudentDetails(22224444);
//
//            System.out.println("Student: " + student);
//        }
//        else
//            System.out.println("Not logged in - try again");


        //mgr.saveToFile();

    }

    private static void printMenu()
    {
        System.out.println("\nEnter your choice:");
        System.out.println("\t1) Display a course:");
        System.out.println("\t2) Display all courses:");
        System.out.println("\t3) Display current course choices:");
        System.out.println("\t4) Update current course choices:");
        System.out.println("\t5) QUIT");

        System.out.println("\nYOU SELECTED --->");
    }
}

// TODO: 13/02/2021 - Create methods for adding data from a file
