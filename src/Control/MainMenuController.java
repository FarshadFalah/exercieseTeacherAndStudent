package Control;

import Control.exceptions.StudentNotFoundException;
import Control.exceptions.TeacherNotFoundException;
import model.Person.Student;
import model.Person.Teacher;
import view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenuController {
    private static MainMenuController mainMenu = null;
    ArrayList<Teacher> teachers;
    ArrayList<Student> students;
    Scanner commandScanner;

    private MainMenuController() {
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        commandScanner = new Scanner(System.in);
    }

    public static MainMenuController getInstance() {

        if (mainMenu == null) {
            mainMenu = new MainMenuController();
        }
        return mainMenu;
    }

    private String askChoice() {
        System.out.print("Please Enter Your Choice: ");
        return commandScanner.nextLine();
    }

    public void mainMenu() {
        preLoad();
        while (true) {
            System.out.print(Menu.mainMenu());
            switch (askChoice()) {
                case "1":
                    teachersMenu();
                    break;
                case "2":
                    studentsMenu();
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Wrong Choice");
                    break;
            }
        }


    }

    private void preLoad() {
        System.out.println("**********Hello and Welcome To Student and Teacher Exercise*******");
        System.out.print("Please Enter File Name for Student List: ");
        File file = new File(commandScanner.nextLine());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                students.add(new Student(scanner.nextLine()));
            }
            System.out.println("Students Added Successfully");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Find");
        }

        System.out.print("Please Enter File Name for Teachers List: ");
        file = new File(commandScanner.nextLine());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                teachers.add(new Teacher(scanner.nextLine()));
            }
            System.out.println("Teachers Added Successfully");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Find");
        }

        System.out.print("Please Enter Classroom File: ");
        file = new File(commandScanner.nextLine());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] s = scanner.nextLine().split(" ");
                try {
                    Student student = new Student(s[0]);
                    Teacher teacher = new Teacher(s[1]);
                    if (!students.contains(student)) throw new StudentNotFoundException();
                    if (!teachers.contains(teacher)) throw new TeacherNotFoundException();
                    students.remove(student);
                    teachers.remove(teacher);
                    student.setTeacher(teacher);
                    teacher.addStudent(student);
                    teachers.add(teacher);
                    students.add(student);
                } catch (StudentNotFoundException e) {
                    System.err.println("Student Do not Exist");
                } catch (TeacherNotFoundException e) {
                    System.err.println("Teacher Not Found");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Wrong Entry");
                }
            }
            System.out.println("Classrooms Created Successfully");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Find");
        }

    }

    private void teachersMenu() {
        while (true) {
            System.out.println(Menu.teachers());
            switch (askChoice()) {
                case "1":
                    System.out.print("Please Enter Teacher's name: ");
                    String t = commandScanner.nextLine();
                    if (teachers.contains(new Teacher(t))) {
                        teacherMenu(t);
                    } else {
                        System.err.println("Teacher Not Found");
                    }
                    break;
                case "2":
                    System.err.print("ARE YOU SURE????? (Y/N)");
                    if (commandScanner.nextLine().matches("Y")) {
                        teachers.clear();
                        System.err.println("TEACHERS REMOVED. ALL CLASSES ARE CANCELED. GOODBYE\n");
                        return;
                    }
                    System.out.println("Classes Saved for Now");
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Wrong Value");
                    break;
            }
        }
    }

    private void studentsMenu() {
        while (true) {
            System.out.println(Menu.students());
            switch (askChoice()) {
                case "1":
                    System.out.println("Please Enter Student's name");
                    String t = commandScanner.nextLine();
                    if (students.contains(new Student(t))) {
                        studentMenu(t);
                    } else {
                        System.err.println("Student Not Found");
                    }
                    break;
                case "2":
                    System.err.print("ARE YOU SURE????? (Y/N)");
                    if (commandScanner.nextLine().matches("Y")) {
                        students.clear();
                        System.err.println("STUDENTS REMOVED. ALL CLASSES ARE CANCELED. GOODBYE");
                        return;
                    }
                    System.out.println("Classes Saved for Now");
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Wrong Value");
                    break;
            }
        }
    }


    private void studentMenu(String name) {
        while (true) {
            System.out.println(Menu.student(name));
            switch (askChoice()) {
                case "1":
                    Student student = new Student(name);
                    int size = students.size();
                    for (int i = 0; i < size; i++) {
                        Student student1 = students.get(i);
                        if (student1.equals(student)) {
                            System.out.println(student1.getTeacher());
                            break;
                        }
                    }
                    break;
                case "2":
                    return;
                default:
                    System.err.println("Wrong Value");
                    break;
            }
        }
    }

    private void teacherMenu(String name) {
        Teacher teacher = new Teacher(name);
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).equals(teacher)) {
                teacher = teachers.get(i);
                break;
            }
        }
        while (true) {
            System.out.println(Menu.teacher(name));
            switch (askChoice()) {
                case "1":
                    System.out.println("Students Are: ");
                    teacher.getStudents().forEach(System.out::println);
                    break;
                case "2":
                    System.out.print("Please Enter Student name: ");
                    String nameStudent = commandScanner.nextLine();
                    Student student = new Student(nameStudent);
                    if (teacher.removeStudent(student)) {
                        for (int i = 0; i < students.size(); i++) {
                            Student student1 = students.get(i);
                            if (student1.equals(student)) {
                                student1.removeTeacher(teacher);
                                break;
                            }
                        }
                        System.out.println(nameStudent + " is Removed");
                    } else {
                        System.out.println(nameStudent + " is not Exist");
                    }
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Wrong Value");
                    break;
            }
        }
    }


}
