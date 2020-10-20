package model.Person;

public class Student extends Person {

    Teacher teacher;
    boolean passed;

    public Student(String name) {
        super(name);
        passed = Math.random() * 10 % 2 == 0;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void removeTeacher(Teacher teacher){
        this.teacher=null;
    }
}