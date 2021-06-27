package project;

import project.appearance.UserMenu;

public class Application {
    public static void main(String[] args) {
        UserMenu userMenu = UserMenu.getInstance();
        userMenu.showMainMenu();



    }
}
