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

import java.util.*;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access
    private final Map<String, Course> allCourses = new HashMap<>();

    //  TODO - convert back and forth map to list etc...

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

        // add in dummy courses
        Course c1 = new Course("COU0001","8","Computing","DKIT");
        Course c2 = new Course("COU0043","6","Business","DKIT");
        Course c3 = new Course("COU0111","7","French","Newry IT");
        Course c4 = new Course("COU0022","6","Music","Athlone IT");
        Course c5 = new Course("COU1044","8","Sport","UCD");

//        // add in course choices

        List<Course> courseChoices = new ArrayList<>();
        courseChoices.add(c1);
        courseChoices.add(c2);
        courseChoices.add(c3);
        courseChoices.add(c4);
        courseChoices.add(c5);

        studentsCourseChoices.put(1234,courseChoices);

        courseChoices = new ArrayList<>();

        courseChoices.add(c5);
        courseChoices.add(c4);
        courseChoices.add(c3);
        courseChoices.add(c2);
        courseChoices.add(c1);

        studentsCourseChoices.put(6398,courseChoices);
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

    // pass in cao number and what they want to change

//    void updateChoice(int caoNumber, List<String> courseChoices) {
//
//    }
//
    public List<Course> getAllCourses() {
        return courseManager.getAllCourses();
    }
//
//    boolean login(int caoNumber, String dob, String password) {
//
//
//    }
}





// TODO - WRITE TO FILE COURSE CHOICES MANAGER
