package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects


import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManager {

    private final Map<Integer, Student> students = new HashMap<>();

    public StudentManager() {
        // Hardcode some values to get started

//        Student s1 = new Student(1234,"05/12/1991","hello","student1@email.ie");
//        Student s2 = new Student(6398,"12/11/1996","well","student2@gmail.ie");
//        Student s3 = new Student(4322,"26/04/1995","howsThings","student3@gmail.ie");
//        Student s4 = new Student(5543,"09/12/1993","grandYourself","student4@gmail.ie");
//        Student s5 = new Student(9121,"03/06/1999","yeaFineThanks","student5@gmail.ie");
//        students.put(1234,s1);
//        students.put(6398,s2);
//        students.put(4322,s3);
//        students.put(5543,s4);
//        students.put(9121,s5);

        // later, load from text file "students.dat" and populate studentsMap

        File inputFile = new File("students.dat");

        try (Scanner scan = new Scanner(inputFile))
        {
            while (scan.hasNextLine())
            {
                String line = scan.nextLine();
                String [] data = line.split(",");

                int caoNumber = Integer.parseInt(data[0]);
                String dateOfBirth = data[1];
                String password = data[2];
                String email = data[3];

                Student student = new Student(caoNumber,dateOfBirth,password,email);
                students.put(caoNumber,student);
            }

        } catch ( FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught." + exception);
        } catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchException caught." + exception);
        }
    }

    public Student getStudent(int caoNumber) {
        return students.containsKey(caoNumber) ? new Student(students.get(caoNumber)) : null;
    }

    public void addStudent(Student stud) {

        if(stud != null)
        {
            Student student = new Student(stud);
            students.put(student.getCaoNumber(),student);
        }
    }

    public void removeStudent(int caoNumber) {
        students.remove(caoNumber);
    }

//    isRegistered( caoNumber)
//        students.isValid()

    @Override
    public String toString()
    {
        return "StudentManager{" +
                "students=" + students +
                '}';
    }
}

// TODO - WRITE TO FILE STUDENT MANAGER
