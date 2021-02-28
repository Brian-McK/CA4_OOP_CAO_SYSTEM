package dkit.oop;

// Stores all student CAO choices.
// Allows student to make course choices, save them and update them later.
//
// emphasis on speed of access when multiple users are accessing this at same time
//
// This component would interact with a Network component that would, in turn, send
// data over the internet to a web client.
//
// Clone all received and returned objects - encapsulation

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access
    private final Map<String, Course> allCourses = new HashMap<>();

    // caoNumber, course selection list - for fast access
    // in order of choices

    private final Map<Integer, List<Course>> studentsCourseChoices = new HashMap<>();

    // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
    // student details and course details.  So, we receive a reference to each via
    // the constructor.
    // This is called "Dependency Injection", meaning that we
    // inject (or pass in) objects that this class requires to do its job.
    //
    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;

        coursesListToMap();

        File inputFile = new File("choices.dat");

        try (Scanner scan = new Scanner(inputFile))
        {
            while (scan.hasNextLine())
            {
                String line = scan.nextLine();
                String [] data = line.split(",");

                int caoNumber = Integer.parseInt(data[0]);
                List <Course> courseChoicesList = new ArrayList<>();
                for (int i = 1; i < data.length; i++)
                {
                    Course course = new Course(getCourseDetails(data[i]));
                    courseChoicesList.add(course);
                }
                studentsCourseChoices.put(caoNumber,courseChoicesList);
            }

        } catch ( FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught." + exception);
        } catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchException caught." + exception);
        }
    }

    public Student getStudentDetails(int caoNumber) {

        if(studentManager.getStudent(caoNumber) != null)
        {
            return studentManager.getStudent(caoNumber);
        }
        else {
            return null;
        }
    }

    public Course getCourseDetails(String courseId) {
        if(courseManager.getCourse(courseId) != null)
        {
            return courseManager.getCourse(courseId);
        }
        else {
            return null;
        }
    }

    public List<Course> getStudentChoices(int caoNumber) {
        return studentsCourseChoices.get(caoNumber);
    }

    public void coursesListToMap()
    {
        List<Course> courses = getAllCourses();

        for(Course course : courses)
        {
            allCourses.put(course.getCourseId(),course);
        }
    }

    // TODO - UPDATE CHOICE
    public void updateChoice(int caoNumber, List<Course> updatedChoicesList) {
        studentsCourseChoices.get(caoNumber).clear();
        studentsCourseChoices.get(caoNumber).addAll(updatedChoicesList);
    }

    public List<Course> getAllCourses() {
        return courseManager.getAllCourses();
    }

    public boolean isRegistered(int caoNumber)
    {
        boolean isRegisteredStudent = false;

        if(getStudentDetails(caoNumber) != null)
        {
            isRegisteredStudent = true;
        }
        return isRegisteredStudent;
    }

    public boolean validLoginStudent(int caoNumber, String dateOfBirth, String password)
    {
        // 1. check caoNumber exists by using is registered method above
        // 2. create a temp new student
        // 2. check their dateOfBirth & password matches the temp students
        boolean isValidLoginStudent = false;

        if (isRegistered(caoNumber))
        {
            Student tempStudent = new Student(getStudentDetails(caoNumber));

            System.out.println(tempStudent);

            if(tempStudent.getDayOfBirth().equals(dateOfBirth) && tempStudent.getPassword().equals(password))
            {
                isValidLoginStudent = true;
            }
        }
        return isValidLoginStudent;
    }
}

// TODO - WRITE TO FILE COURSE CHOICES MANAGER
