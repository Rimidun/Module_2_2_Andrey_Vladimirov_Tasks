package project.view;

import project.controller.UserController;
import project.controller.WriterController;
import project.entity.Label;
import project.entity.Post;
import project.entity.Writer;
import project.entity.dto.WriterDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController;
    private final PostView postView;
    private final LabelView labelView;
    private final UserController userController;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public WriterView() {
        this.writerController = new WriterController();
        this.postView = new PostView();
        this.labelView = new LabelView();
        this.userController = new UserController();
    }

    public void createUserDialog() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = matchName();

        System.out.print("Enter the last name of the user: ");
        String lastName = matchName();

        Label label = labelView.createLabelDialog();
        List<Post> posts = postView.createPostDialog(null);
        WriterDto writer = new WriterDto(null, firstName, lastName, posts, label);

        System.out.println("User created:");
        printWriter(writer);
        System.out.print(ANSI_RED + "Save user (Y/N)?: " + ANSI_RESET);

        String i = sc.nextLine();
        if ("y".equals(i.toLowerCase())) {
            writer = userController.save(writer);
            if (writer != null) {
                System.out.println(ANSI_GREEN + "User saved." + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "User not saved." + ANSI_RESET);
            }
        }
        sleep();
    }

    public void searchUserDialog(int select) {
        switch (select) {
            case 1: {
                System.out.print("Enter first name: ");
                String firstName = matchName();
                Writer find = writerController.getByFirstName(firstName);
                System.out.println(find);
                break;
            }
            case 2: {
                System.out.print("Enter first name: ");
                String lastName = matchName();
                Writer find = writerController.getByLastName(lastName);
                System.out.println(find);
                break;
            }
            case 3: {
                System.out.println("Enter the user's first and last name: ");
                System.out.print("Enter first name: ");
                String firstName = matchName();
                System.out.print("Enter last name: ");
                String lastName = matchName();
                System.out.println(writerController.getByFirstName(firstName));
                System.out.println(writerController.getByFirstName(lastName));
                break;
            }
            case 4: {
                System.out.print("Enter user post: ");
                String content = matchName();
                Post post = postView.get(content);
                System.out.println(writerController.get(post.getId()));
                break;
            }
            case 5: {
                System.out.print("Enter user tag: ");
                String label = matchName();
                Label find = labelView.getLabel(label);
                System.out.println(writerController.getByLabel(find.getId()));
                break;
            }
        }
    }

    public void removeUserDialog() {
        System.out.print("Enter the user id to delete: ");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();
        WriterDto writer = userController.get(id);
        printWriter(writer);
        System.out.print(ANSI_RED + "User will be deleted, confirm (Y/N): " + ANSI_RESET);
        String input = sc.next();
        if ("y".equals(input.toLowerCase())) {
            userController.remove(id);
            if (writerController.get(writer.getId()) == null) {
                System.out.println(ANSI_GREEN + "User deleted." + ANSI_RESET);
            } else System.err.println("Unable to delete user \"" +
                    writer.getFirstName() + writer.getLastName() +
                    "\"" + ", or the user does not exist.");
        }
    }
    //
    public void updateUserDialog() {
        System.out.print("Enter user id to edit: ");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();

        WriterDto writer = userController.get(id);
        if (writer == null) {
            System.out.println(ANSI_GREEN + "User with id: " + id + " not found." + ANSI_RESET);
            return;
        }

        System.out.println("Editable user:");
        printWriter(writer);

        System.out.println("Make new entries or press Enter to skip:");
        System.out.print("Enter first name: ");
        String input = sc.nextLine();
        if (!input.equals("")) {
            writer.setFirstName(input);
        }
        System.out.print("Enter last name: ");
        input = sc.nextLine();
        if (!input.equals("")) {
            writer.setLastName(input);
        }
        System.out.print("Enter the tag: ");
        input = sc.nextLine();
        Label label = new Label(input);

        System.out.println("Edit posts, or press Enter to skip: ");
        List<Post> postList = new ArrayList<>();

        for (Post post : postView.getAll(writer.getId())) {
            System.out.println(ANSI_GREEN + post.getContent() + ANSI_RESET);
            System.out.print("New entry: ");
            String content = sc.nextLine();
            if (!content.equals("")) {
                post.setContent(content);
                postList.add(post);
            } else postList.add(post);

        }
        printWriter(writer);
        System.out.print(ANSI_RED + "Save user changes? Y/N: " + ANSI_RESET);
        String s = sc.next();
        if ("y".equals(s.toLowerCase())) {
            writer.setLabel(label);
            writer.setPosts(postList);
            userController.update(writer);
            System.out.println(ANSI_GREEN + "User saved." + ANSI_RESET);
        } else System.out.println("Update canceled by user.");
    }

    private String matchName() {
        Scanner sc = new Scanner(System.in);
        String firstName = sc.nextLine();
        while (!firstName.matches("[A-zА-я]+")) {
            System.err.println("You misspelled your name, please try again.");
            System.out.print("Enter first name: ");
            firstName = sc.nextLine();
        }

        return firstName;
    }

    private void printWriter(WriterDto writer) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("id: " + "\"" + writer.getId() + "\"" + "\n")
                .append("First name: " + "\"" + writer.getFirstName() + "\"" + "\n")
                .append("Last name: " + "\"" + writer.getLastName() + "\"" + "\n")
                .append("Tag: " + "\"" + writer.getLabel().getName() + "\"" + "\n")
                .append("Post: \n")
                .append(postView.toString(writer.getPosts()));

        System.out.println(ANSI_GREEN + sb.toString() + ANSI_RESET);
    }

    private void sleep() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press any key to continue...");
        sc.nextLine();
    }
}