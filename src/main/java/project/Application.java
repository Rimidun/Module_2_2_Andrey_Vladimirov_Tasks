package project;

import project.view.UserMenu;

public class Application {
    public static void main(String[] args) {
        UserMenu userMenu = UserMenu.getInstance();
        userMenu.showMainMenu();



    }
}
