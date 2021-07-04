package project.view;

import java.util.Scanner;

public class UserMenu {
    private static UserMenu INSTANCE;
    private final WriterView writerView;

    private UserMenu() {
        this.writerView = new WriterView();
    }

    public static UserMenu getInstance() {
        if (INSTANCE == null) {
            synchronized (UserMenu.class) {
                if(INSTANCE == null){
                    INSTANCE = new UserMenu();
                }
            }
        }

        return INSTANCE;
    }

    public void showMainMenu() {
        System.out.println("Select an action:");
        System.out.println();
        System.out.println("[1] - Add user.");
        System.out.println("[2] - Find user.");
        System.out.println("[3] - Change user.");
        System.out.println("[4] - Delete user.");
        System.out.println("[0] - Exit.");

        switch (matchMenuNumber()) {
            case 1: {
                createUserMenu();
            }
            case 2: {
                searchUserMenu();
            }
            case 3: {
                updateUserMenu();
            }
            case 4: {
                removeUserMenu();
            }
            case 0: {
                System.exit(0);
            }
            default: {
                System.out.println("Non-existent menu item selected.");
                showMainMenu();
            }
        }
    }

    public void createUserMenu() {
        System.out.println("User creation menu.");
        writerView.createUserDialog();
        showMainMenu();
    }

    public void searchUserMenu() {
        System.out.println("User search menu. Select your search criteria:");
        System.out.println("[1] - Search by first name.");
        System.out.println("[2] - Search by last name.");
        System.out.println("[3] - Search by first name and last name.");
        System.out.println("[4] - Search by content.");
        System.out.println("[5] - Search by tag.");
        System.out.println("[6] - Exit to the main menu.");
        System.out.println("[0] - Exit.");

        int select = matchMenuNumber();
        switch (select) {
            case 1: {
                writerView.searchUserDialog(1);
                sleep();
                searchUserMenu();
            }
            case 2: {
                writerView.searchUserDialog(2);
                sleep();
                searchUserMenu();
            }
            case 3: {
                writerView.searchUserDialog(3);
                sleep();
                searchUserMenu();
            }
            case 4: {
                writerView.searchUserDialog(4);
                sleep();
                searchUserMenu();
            }
            case 5: {
                writerView.searchUserDialog(5);
                sleep();
                searchUserMenu();
            }
            case 6: {
                showMainMenu();
            }
            case 0: {
                System.exit(0);
            }
            default: {
                System.out.println("Non-existent menu item selected.");
                searchUserMenu();
            }
        }

        showMainMenu();
    }

    public void removeUserMenu(){
        System.out.println("Delete user menu:");
        writerView.removeUserDialog();
        showMainMenu();
    }

    public void updateUserMenu(){
        System.out.println("User edit menu:");
        writerView.updateUserDialog();
        showMainMenu();
    }


    private int matchMenuNumber() {
        int select = -1;

        Scanner sc = new Scanner(System.in);
        System.out.print("Select a menu item: ");
        String input = sc.nextLine();

        while (!input.matches("[0-9]")) {
            System.out.println("Input error, try again");
            System.out.print("Select a menu item: ");
            input = sc.nextLine();
        }

        try {
            select = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error");
        }

        return select;
    }

    private void sleep() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press any key to continue ...");
        sc.nextLine();
    }
}