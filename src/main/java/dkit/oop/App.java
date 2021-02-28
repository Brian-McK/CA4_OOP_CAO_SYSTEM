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
    public static void main(String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    public void start() {

        Scanner scan = new Scanner(System.in);

        StudentManager studentManager = new StudentManager();
        CourseManager courseManager= new CourseManager();
        CourseChoicesManager courseChoicesManager = new CourseChoicesManager(studentManager, courseManager);

        System.out.println("Welcome to the CAO, Enter details to login:");

        //TODO - VALIDATE
        System.out.println("Enter CAO Number: ");
//        int caoNumber = scan.nextInt();
//        scan.nextLine();
        int caoNumber = 21885454;

        System.out.println("Enter date of birth: ");
//        String dateOfBirth = scan.nextLine();
        String dateOfBirth = "1994-10-29";

        System.out.println("Enter Password: ");
//        String password = scan.nextLine();
        String password = "c@rROTsS";

        boolean isValidLoginStudent = courseChoicesManager.validLoginStudent(caoNumber,dateOfBirth,password);

        if(!isValidLoginStudent)
        {
            System.out.println("Try again");
        }
        else {
            System.out.println("Congrats you are logged in: " + caoNumber);
            Student student = new Student(courseChoicesManager.getStudentDetails(caoNumber));
            studentMenuOptions(student, courseChoicesManager);
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
    }

    public static void studentMenuOptions(Student student, CourseChoicesManager courseChoicesManager)
    {
        Scanner scan = new Scanner(System.in);

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
                        System.out.println("\nYOU SELECTED ---> 1) Display a course");
                        System.out.println("Enter Course ID:");
                        String courseId = scan.nextLine();
                        //TODO - VALIDATE
                        if(courseChoicesManager.getCourseDetails(courseId) != null)
                        {
                            System.out.println(courseChoicesManager.getCourseDetails(courseId));
                        }
                        else {
                            System.out.println("Course does not exist");
                        }
                        break;
                    case DISPLAY_ALL_COURSES:
                        System.out.println("\nYOU SELECTED ---> 2) Display all courses");
                        //TODO - Make output nicer and sort
                        System.out.println(courseChoicesManager.getAllCourses());
                        break;
                    case DISPLAY_CURRENT_CHOICES:
                        System.out.println("\nYOU SELECTED ---> 3) Display current course choices:");
                        System.out.println(courseChoicesManager.getStudentChoices(student.getCaoNumber()));
//                        displayCurrentCourseChoices();
                        break;
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
    }
}
