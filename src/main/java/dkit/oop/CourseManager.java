package dkit.oop;


import java.util.*;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

public class CourseManager {

    private final Map<String, Course> courses = new HashMap<>();

    public CourseManager() {
        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap

        Course c1 = new Course("COU0001","8","Computing","DKIT");
        Course c2 = new Course("COU0043","6","Business","DKIT");
        Course c3 = new Course("COU0111","7","French","Newry IT");
        Course c4 = new Course("COU0022","6","Music","Athlone IT");
        Course c5 = new Course("COU1044","8","Sport","UCD");

        courses.put("COU0001",c1);
        courses.put("COU0043",c2);
        courses.put("COU0111",c3);
        courses.put("COU0022",c4);
        courses.put("COU1044",c5);

    }

    public Course getCourse(String courseId) {
        return courses.containsKey(courseId) ? new Course(courses.get(courseId)) : null;
    }

//    linked list or arraylist???

    public List<Course> getAllCourses() {

        List<Course> allCourses = new LinkedList<>();

        for (String courseKey: courses.keySet()) {
            allCourses.add(courses.get(courseKey));
        }

        return allCourses;
    }

    public void addCourse(Course course) {

        if(course != null)
        {
            Course course1 = new Course(course);
            courses.put(course.getCourseId(),course1);
        }
    }

    public void removeCourse(String courseId) {
        courses.remove(courseId);
    }

    // editCourse(courseId);       // not required for this iteration

}







