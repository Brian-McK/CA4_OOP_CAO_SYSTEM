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
    private static final String DATE_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    // ^                # start-of-string
    //(?=.*[0-9])       # a digit must occur at least once
    //(?=.*[a-z])       # a lower case letter must occur at least once
    //(?=.*[A-Z])       # an upper case letter must occur at least once
    //(?=.*[@#$%^&+=])  # a special character must occur at least once
    //(?=\S+$)          # no whitespace allowed in the entire string
    //.{8,}             # anything, at least eight places though
    //$                 # end-of-string
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

        int userChoice;

        do {
            System.out.println("Welcome to the CAO, Login as Student (1) or Admin (2): ");

            while (!scan.hasNextInt())
            {
                String input = scan.next();
                System.out.printf("%s is not a valid number.\n", input);
            }
            userChoice = scan.nextInt();

        } while (userChoice < 1 || userChoice > 2);

        if(userChoice == 1)
        {
            // do student things
            System.out.println("Welcome student, Enter details to login:");

            System.out.println("Enter CAO Number: ");
            while (!scan.hasNextInt())
            {
                System.out.println("Must be a CAO Number, no letters: ");
                scan.next();
            }
            int caoNumber = scan.nextInt();
            scan.nextLine();
            System.out.println("CAO NUMBER: " + caoNumber);

//          int caoNumber = 21885454;

            System.out.println("Enter date of birth (yyyy-mm-dd): ");
            String dateOfBirth = scan.nextLine();

            while (!(dateOfBirth.matches(DATE_REGEX)))
            {
                System.out.println("Invalid entry, please enter date of birth again (yyyy-mm-dd): ");
                dateOfBirth = scan.nextLine();
            }
            System.out.println("DOB: " + dateOfBirth);

//          String dateOfBirth = "1994-10-29";

            System.out.println("Enter Password: ");
            String password = scan.nextLine();

            while (!(password.matches(PASSWORD_REGEX)))
            {
                System.out.println("Password must contain uppercase / lowercase / special chars / no white spaces and" +
                        " at least 8 digits long" +
                        "  ");
                password = scan.nextLine();
            }
            System.out.println("Password: " + password);

//          String password = "c@rROTsS1!";

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
        else
        {
            System.out.println("Welcome admin, Enter details to login:");

            System.out.println("Enter Username: ");
//            String username = scan.nextLine();
            String username = "admin";

            System.out.println("Enter Password: ");
//            String password = scan.nextLine();
            String password = "admin";

            if(username.equals("admin") && password.equals("admin"))
            {
                System.out.println("Congrats you are logged in: " + username);
                adminMenuOptions(courseChoicesManager,studentManager, courseManager);
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
                        System.out.println(courseChoicesManager.getAllCourses());
                        break;
                    case DISPLAY_CURRENT_CHOICES:
                        System.out.println("\nYOU SELECTED ---> 3) Display current course choices:");
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

                        System.out.println("\nUpdated Course Choices: " + courseChoicesManager.getStudentChoices(student.getCaoNumber()));
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

    public static void adminMenuOptions(CourseChoicesManager courseChoicesManager, StudentManager studentManager,
                                        CourseManager courseManager)
    {
        Scanner scan = new Scanner(System.in);

        // student menu
        AdminMenuOptions selectedOptionStudent = AdminMenuOptions.CONTINUE;
        while (selectedOptionStudent != AdminMenuOptions.QUIT)
        {
            try{
                printMenuAdmin();
                selectedOptionStudent = AdminMenuOptions.values()[Integer.parseInt(scan.nextLine().trim())];

                switch (selectedOptionStudent)
                {
                    case ADD_COURSE:
                        System.out.println("\nYOU SELECTED ---> 1) Add a course");
                        System.out.println("Enter new course Id: ");
                        String newCourseId = scan.nextLine();

                        // check if the courseId is available ie not in the list
                        if(courseChoicesManager.getCourseDetails(newCourseId) != null)
                        {
                            System.out.println("Invalid, try again");
                        }
                        else {
                            System.out.println("Enter the level: ");
                            String level = scan.nextLine();

                            System.out.println("Enter the title: ");
                            String title = scan.nextLine();

                            System.out.println("Enter the institution: ");
                            String institution = scan.nextLine();

                            Course newCourse = new Course(newCourseId,level,title,institution);
                            courseManager.addCourse(newCourse);
                            System.out.println(courseChoicesManager.getAllCourses());
                        }
                        break;
                    case REMOVE_COURSE:
                        System.out.println("\nYOU SELECTED ---> 2) Remove a course");
                        System.out.println("Enter course id to remove: ");
                        String courseIdRemove = scan.nextLine();

                        if(courseChoicesManager.getCourseDetails(courseIdRemove) == null)
                        {
                            System.out.println("Invalid, try again");
                        }
                        else {
                            courseManager.removeCourse(courseIdRemove);
                            System.out.println("Removed course: " + courseIdRemove);
                            System.out.println(courseChoicesManager.getAllCourses());
                        }
                        break;
                    case DISPLAY_ALL_COURSES:
                        System.out.println("\nYOU SELECTED ---> 3) Display all courses");
                        System.out.println(courseChoicesManager.getAllCourses());
                        break;
                    case DISPLAY_COURSE:
                        System.out.println("\nYOU SELECTED ---> 4) Display a course");
                        System.out.println("Enter Course ID:");
                        String courseIdDisplay = scan.nextLine();
                        if(courseChoicesManager.getCourseDetails(courseIdDisplay) != null)
                        {
                            System.out.println(courseChoicesManager.getCourseDetails(courseIdDisplay));
                        }
                        else {
                            System.out.println("Course does not exist");
                        }
                        break;
                    case ADD_STUDENT:
                        System.out.println("\nYOU SELECTED ---> 5) Add a student");

                        System.out.println("Enter new student cao number: ");
                        int newStudentCao = scan.nextInt();
                        scan.nextLine();

                        if(courseChoicesManager.getStudentDetails(newStudentCao) != null)
                        {
                            System.out.println("Invalid, try again");
                        }
                        else {
                            System.out.println("Enter the date of birth: ");
                            String dob = scan.nextLine();

                            System.out.println("Enter the password: ");
                            String password = scan.nextLine();

                            System.out.println("Enter the email: ");
                            String email = scan.nextLine();

                            Student newStudent = new Student(newStudentCao,dob,password,email);
                            studentManager.addStudent(newStudent);
                            System.out.println(studentManager.getStudent(newStudent.getCaoNumber()));
                        }

                        break;
                    case REMOVE_STUDENT:
                        System.out.println("\nYOU SELECTED ---> 6) Remove a student");
                        System.out.println("Enter cao number to remove student: ");
                        int studentCaoRemove = scan.nextInt();
                        scan.nextLine();
                        // check if it exists
                        studentManager.removeStudent(studentCaoRemove);
                        System.out.println("Removed student: " + studentCaoRemove);
                        break;
                    case DISPLAY_STUDENT:
                        System.out.println("\nYOU SELECTED ---> 7) Display a students details: ");
                        System.out.println("Enter student CAO number:");
                        int studentCaoNumber = scan.nextInt();
                        scan.nextLine();
                        if(studentManager.getStudent(studentCaoNumber) != null)
                        {
                            System.out.println(studentManager.getStudent(studentCaoNumber));
                        }
                        else {
                            System.out.println("Student does not exist");
                        }
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
