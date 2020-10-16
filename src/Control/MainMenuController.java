package Control;

import Control.exceptions.StudentNotFound;
import Control.exceptions.TeacherNotFound;
import model.Student;
import model.Teacher;
import view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenuController {
    static Map<Student,Teacher> classroom=new HashMap<>();
    static ArrayList<Teacher> teachers=new ArrayList<>();
    static ArrayList<Student> students=new ArrayList<>();

    public static void mainMenu() {
        preLoad();
        System.out.print(Menu.mainMenu());
        while (true) {
            switch (CommandScannerWrapper.nextLineMenu()) {
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

    private static void preLoad(){
        System.out.println("**********Hello and Welcome To Student and Teacher Exercise*******");
        System.out.print("Please Enter File Name for Student List: ");
        File file=new File(CommandScannerWrapper.nextLine());
        try(Scanner scanner=new Scanner(file)) {
            while (scanner.hasNext()){
                students.add(new Student(scanner.nextLine()));
            }
            System.out.println("Students Added Successfully\n");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Find");
        }

        System.out.print("Please Enter File Name for Teachers List: ");
        file=new File(CommandScannerWrapper.nextLine());
        try(Scanner scanner=new Scanner(file)) {
            while (scanner.hasNext()){
                teachers.add(new Teacher(scanner.nextLine()));
            }
            System.out.println("Teachers Added Successfully");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Find");
        }

        System.out.println("Please Enter Classroom File: ");
        file=new File(CommandScannerWrapper.nextLine());
        try(Scanner scanner=new Scanner(file)) {
            while (scanner.hasNext()){
                String[] s=scanner.nextLine().split(" ");
                try {
                    Student student=new Student(s[0]);
                    Teacher teacher=new Teacher(s[1]);
                    if (!students.contains(student)) throw new StudentNotFound();
                    if (!teachers.contains(teacher)) throw new TeacherNotFound();
                    classroom.put(student,teacher);

                } catch (StudentNotFound e) {
                    System.err.println("Student Do not Exist");
                }catch (TeacherNotFound e){
                    System.err.println("Teacher Not Found");
                }catch (ArrayIndexOutOfBoundsException e){
                    System.err.println("Wrong Entry");
                }
            }
            System.out.println("Classrooms Created Successfully");
        } catch (FileNotFoundException e) {
            System.err.println("File Not Find");
        }



    }

    private static void teachersMenu(){
        System.out.println(Menu.teachers());
        while (true){
            switch (CommandScannerWrapper.nextLineMenu()) {
                case "1":
                    System.out.println("Please Enter Teacher's name");
                    String t=CommandScannerWrapper.nextLine();
                    if(teachers.contains(new Teacher(t))) {
                        teacherMenu(t);
                    }else {
                        System.err.println("Teacher Not Found");
                    }
                    break;
                case "2":
                    System.err.print("ARE YOU SURE????? (Y/N)");
                    if(CommandScannerWrapper.nextLine().matches("Y")){
                        teachers.clear();
                        classroom.clear();
                        System.err.println("TEACHERS REMOVED. ALL CLASSES ARE CANCELED. GOODBYE");
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

   private static void studentsMenu(){
        System.out.println(Menu.students());
        while (true){
            switch (CommandScannerWrapper.nextLineMenu()) {
                case "1":
                    System.out.println("Please Enter Student's name");
                    String t=CommandScannerWrapper.nextLine();
                    if(students.contains(new Student(t))) {
                        studentMenu(t);
                    }else {
                        System.err.println("Student Not Found");
                    }
                    break;
                case "2":
                    System.err.print("ARE YOU SURE????? (Y/N)");
                    if(CommandScannerWrapper.nextLine().matches("Y")){
                        students.clear();
                        classroom.clear();
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


    private static void studentMenu(String name){
        System.out.println(Menu.student(name));
        while (true){
            switch (CommandScannerWrapper.nextLineMenu()) {
                case "1":
                    Student student=new Student(name);
                    System.out.println(classroom.get(student));
                    break;
                case "2":

                    return;
                default:
                    System.err.println("Wrong Value");
                    break;
            }
        }
    }

    private static void teacherMenu(String name){
        System.out.println(Menu.teacher(name));
        Teacher teacher=new Teacher(name);

        while (true){
            switch (CommandScannerWrapper.nextLineMenu()) {
                case "1":
                    System.out.println("Students Are: ");
                    classroom.forEach((k,v)->{
                        if(v.equals(teacher)){
                            System.out.print(k+", ");
                        }
                    });
                    break;
                case "2":
                    System.out.print("Please Enter Student name: ");
                    Student student=new Student(CommandScannerWrapper.nextLine());
                    if (classroom.containsKey(student)) {
                        classroom.remove(student);
                    }else
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
