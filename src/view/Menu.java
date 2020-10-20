package view;

public class Menu {
    public static String mainMenu() {
        return "1-Teachers\n" +
                "2-Students\n" +
                "3-Exit";
    }

    public static String teachers() {
        return "(Teachers)\n" +
                "1-Find Teacher\n" +
                "2-Remove All Teachers???!!!\n" +
                "3-Back";
    }

    public static String students() {
        return "(Students)\n" +
                "1-Find Student\n" +
                "2-Remove All Students :D :D :D\n" +
                "3-back";
    }

    public static String teacher(String name) {
        return "(" + name + ")\n" +
                "1-Show Student List\n" +
                "2-Remove a Student\n" +
                "3-back";
    }

    public static String student(String name) {
        return "(" + name + ")\n" +
                "1-Show Teacher\n" +
                "2-back";
    }


}
