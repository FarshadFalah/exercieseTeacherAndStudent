package Control;

import java.util.Scanner;

public class CommandScannerWrapper {
    private static final Scanner scanner=new Scanner(System.in);

    public static String nextLine(){
        return scanner.nextLine();
    }
    public static String nextLineMenu(){
        System.out.print("please Enter Your Choice");
        return scanner.nextLine();
    }

}
