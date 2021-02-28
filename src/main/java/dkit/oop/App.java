package dkit.oop;

import java.util.ArrayList;
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
    private static final int MAX_NUM_COURSE_CHOICES = 10;

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

        //TODO - VALIDATE
        System.out.println("Welcome to the CAO, Login as Student (1) or Admin (2): ");
        int userChoice = scan.nextInt();
        scan.nextLine();

        if(userChoice == 1)
        {
            // do student things
            System.out.println("Welcome student, Enter details to login:");

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

            if(isValidLoginStudent)
            {
                System.out.println("Congrats you are logged in: " + caoNumber);
                Student student = courseChoicesManager.getStudentDetails(caoNumber);
                studentMenuOptions(student, courseChoicesManager);
            }
            else {
                System.out.println("Try again");
            }
        }
        else if(userChoice == 2)
        {
            System.out.println("Welcome admin, Enter details to login:");

            // TODO - VALIDATE
            System.out.println("Enter Username: ");
//            String username = scan.nextLine();
            String username = "admin";

            System.out.println("Enter Password: ");
//            String password = scan.nextLine();
            String password = "admin";

            if(username.equals("admin") && password.equals("admin"))
            {
                System.out.println("Congrats you are logged in: " + username);
                adminMenuOptions(courseChoicesManager);
            }
            else {
                System.out.println("Try again");
            }
        }

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

    private static void printMenuAdmin()
    {
        System.out.println("\nEnter your choice:");
        System.out.println("\t1) Add a course:");
        System.out.println("\t2) Remove a course:");
        System.out.println("\t3) Display all courses:");
        System.out.println("\t4) Display a course:");
        System.out.println("\t5) Add a student:");
        System.out.println("\t6) Remove a student:");
        System.out.println("\t7) Display a student:");
        System.out.println("\t8) QUIT");
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
                        //TODO - Make output nicer and sort
                        System.out.println(courseChoicesManager.getStudentChoices(student.getCaoNumber()));
                        break;
                    case UPDATE_CHOICES:
                        System.out.println("\nYOU SELECTED ---> 4) Update current course choices:");
                        System.out.println("Current Course Choices: " + courseChoicesManager.getStudentChoices(student.getCaoNumber()));
                        System.out.println("Please enter your updated student choices (Max 10 entries): ");
                        List<Course> updatedChoicesList = new ArrayList<>();

                        updatedChoicesList.add(new Course(courseChoicesManager.getCourseDetails("GA780")));
                        updatedChoicesList.add(new Course(courseChoicesManager.getCourseDetails("GA781")));
                        updatedChoicesList.add(new Course(courseChoicesManager.getCourseDetails("GA782")));
                        updatedChoicesList.add(new Course(courseChoicesManager.getCourseDetails("GA783")));
                        updatedChoicesList.add(new Course(courseChoicesManager.getCourseDetails("GA784")));
                        updatedChoicesList.add(new Course(courseChoicesManager.getCourseDetails("GA785")));

                        courseChoicesManager.updateChoice(student.getCaoNumber(),updatedChoicesList);

                        System.out.println("Updated Course Choices: " + courseChoicesManager.getStudentChoices(student.getCaoNumber()));
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

    public static void adminMenuOptions(CourseChoicesManager courseChoicesManager)
    {
        Scanner scan = new Scanner(System.in);

        // student menu
        AdminMenuOptions selectedOptionStudent = AdminMenuOptions.CONTINUE;
        while (selectedOptionStudent != AdminMenuOptions.QUIT)
        {
            try{
                printMenuStudent();
                selectedOptionStudent = AdminMenuOptions.values()[Integer.parseInt(scan.nextLine().trim())];

                switch (selectedOptionStudent)
                {
                    case ADD_COURSE:
                        System.out.println("\nYOU SELECTED ---> 1) Add a course");
                        break;
                    case REMOVE_COURSE:
                        System.out.println("\nYOU SELECTED ---> 2) Remove a course");
                        break;
                    case DISPLAY_ALL_COURSES:
                        System.out.println("\nYOU SELECTED ---> 3) Display all courses");
                        break;
                    case DISPLAY_COURSE:
                        System.out.println("\nYOU SELECTED ---> 4) Display a course");
                        break;
                    case ADD_STUDENT:
                        System.out.println("\nYOU SELECTED ---> 5) Add a student");
                        break;
                    case REMOVE_STUDENT:
                        System.out.println("\nYOU SELECTED ---> 6) Remove a student");
                        break;
                    case DISPLAY_STUDENT:
                        System.out.println("\nYOU SELECTED ---> 7) Display a student");
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
