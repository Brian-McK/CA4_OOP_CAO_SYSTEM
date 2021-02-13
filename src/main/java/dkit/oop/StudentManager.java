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

        Student s1 = new Student(1234,"05/12/1991","hello","student1@email.ie");
        Student s2 = new Student(6398,"12/11/1996","well","student2@gmail.ie");
        Student s3 = new Student(4322,"26/04/1995","howsThings","student3@gmail.ie");
        Student s4 = new Student(5543,"09/12/1993","grandYourself","student4@gmail.ie");
        Student s5 = new Student(9121,"03/06/1999","yeaFineThanks","student5@gmail.ie");
        applicants.put(1234,s1);
        applicants.put(6398,s2);
        applicants.put(4322,s3);
        applicants.put(5543,s4);
        applicants.put(9121,s5);

        // later, load from text file "students.dat" and populate studentsMap
    }

    public Student getStudent(int caoNumber) {
        return applicants.containsKey(caoNumber) ? new Student(applicants.get(caoNumber)) : null;
    }

    public void addStudent(Student stud) {

        if(stud != null)
        {
            Student student = new Student(stud);
            applicants.put(student.getCaoNumber(),student);
        }
    }

    public void removeStudent(int caoNumber) {
        applicants.remove(caoNumber);
    }

//    isRegistered( caoNumber)
//        students.isValid()

    @Override
    public String toString()
    {
        return "StudentManager{" +
                "applicants=" + applicants +
                '}';
    }
}
