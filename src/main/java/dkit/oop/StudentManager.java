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

    @Override
    public String toString()
    {
        return "StudentManager{" +
                "students=" + students +
                '}';
    }
}

// TODO - WRITE TO FILE STUDENT MANAGER
