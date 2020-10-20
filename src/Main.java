import controller.MainMenuController;

public class Main {
    public static void main(String[] args) {
        MainMenuController menuController = MainMenuController.getInstance();
        menuController.mainMenu();
    }
}
