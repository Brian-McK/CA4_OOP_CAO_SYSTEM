package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects


import java.util.HashMap;
import java.util.Map;

public class StudentManager {

    // Store all students in data structure
    // make it private
    // prob use hashmap

    private final Map<Integer, Student> applicants = new HashMap<>();

    public StudentManager() {
        // Hardcode some values to get started

        Student s1 = new Student(1234,"05/12/1991","hello","myemail@email.ie");
        Student s2 = new Student(12345,"07/11/1992","well","myemail@gmail.ie");
        applicants.put(1234,s1);
        applicants.put(12345,s2);

        // later, load from text file "students.dat" and populate studentsMap
    }

    public Student getStudent(int caoNumber) {
        if(applicants.containsKey(caoNumber))
        {
            return new Student(applicants.get(caoNumber));
        }
        else {
            return null;
        }
    }
//
//    public addStudent() {
//    }
//
//    public removeStudent() {
//    }

//    isRegistered( caoNumber)
//        students.isValid()
}
