package dkit.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * Brian McKenna
 * SD2B
 */

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

        File inputFile = new File("courses.dat");

        try (Scanner scan = new Scanner(inputFile))
        {
            while (scan.hasNextLine())
            {
                String line = scan.nextLine();
                String [] data = line.split(",");

                String courseId = data[0];
                String level = data[1];
                String title = data[2];
                String institution = data[3];

                Course course = new Course(courseId,level,title,institution);
                courses.put(courseId,course);
            }

        } catch ( FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught." + exception);
        } catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchException caught." + exception);
        }

    }

    // in main check if null car not found else do something

    public Course getCourse(String courseId) {
        Course course = courses.get(courseId);

        return course != null ? new Course(course) : null;
    }

    public List<Course> getAllCourses() {

        List<Course> allCourses = new ArrayList<>();
        Set<String> keySet = courses.keySet();

        for (String courseId: keySet) {
            Course course = courses.get(courseId);
            allCourses.add(new Course(course));
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


    @Override
    public String toString()
    {
        return "CourseManager{" +
                "courses=" + courses +
                '}';
    }
}
// TODO - WRITE TO FILE COURSE MANAGER







