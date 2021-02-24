package dkit.oop;

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

        Scanner scan = new Scanner(System.in);

        StudentManager studentManager = new StudentManager();
        CourseManager courseManager= new CourseManager();
        CourseChoicesManager courseChoicesManager = new CourseChoicesManager(studentManager, courseManager);

        System.out.println("Welcome to the CAO, Enter details to login:");

        System.out.println("Enter CAO Number: ");
        int caoNumber = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter date of birth: ");
        String dateOfBirth = scan.nextLine();

        System.out.println("Enter Password: ");
        String password = scan.nextLine();

        boolean isValidLoginStudent = studentManager.validLoginStudent(caoNumber,dateOfBirth,password);

        if(!isValidLoginStudent)
        {
            System.out.println("Try again");
        }
        else {
            System.out.println("Congrats your logged in");
        }

        // student menu
        StudentMenuOptions selectedOptionStudent = StudentMenuOptions.CONTINUE;
        while (selectedOptionStudent != StudentMenuOptions.QUIT)
        {
            try{
                printMenuStudent();
                selectedOptionStudent = StudentMenuOptions.values()[Integer.parseInt(scan.nextLine().trim())];

                switch (selectedOptionStudent)
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

    private static void printMenuStudent()
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
