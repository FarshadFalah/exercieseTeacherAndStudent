package model.Person;

import java.util.ArrayList;

public class Teacher extends Person {

    ArrayList<Student> students;

    public Teacher(String name) {
        super(name);
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            return true;
        } else {
            return false;
        }
    }
}