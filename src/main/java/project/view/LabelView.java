package project.view;

import project.controller.LabelController;
import project.entity.Label;

import java.util.Scanner;

public class LabelView {
    private final LabelController labelController;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public LabelView() {
        this.labelController = new LabelController();
    }

    public Label getLabel(String name) {
        return labelController.get(name);
    }

    public void update() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the entry for editing: ");
        String searchName = sc.nextLine();
        System.out.print("Record for editing: ");
        Label search = getLabel(searchName);
        toString(search);
        System.out.print("Enter new value: ");
        String updateLabelName = sc.nextLine();
        System.out.print(ANSI_RED + "The existing entry will be replaced with \"" +
                updateLabelName + "\"" +
                " (Y/N): " +
                ANSI_RESET);

        String input = sc.next();
        if (input.toLowerCase().equals("y")) {
            search.setName(updateLabelName);
            Label result = labelController.update(search);
            if (result != null) {
                System.out.println(ANSI_GREEN +
                        "Post edited to: " + toString(search) +
                        ANSI_RESET);

            }
        } else System.out.println("Editing canceled by user.");

        sc.close();
    }

    public Label save() {
        Label label = labelController.save(createLabelDialog());
        if (label.getId() != null) {
            System.out.println(ANSI_GREEN + "Tag: " + toString(label) + " saved." + ANSI_RESET);
        } else System.err.println("Failed to save.");

        return label;
    }

    public Label createLabelDialog() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the tag: ");
        String label = sc.nextLine();
        while (!matchLabel(label)) {
            System.err.print("You misspelled the tag, please try again.");
            System.out.print("Enter the tag: ");
            label = sc.nextLine();
        }
        return new Label(null, label);
    }

    public String toString(Label label) {
        String prepare = String.format("id:%d name:%s", label.getId(), label.getName());

        return ANSI_GREEN + prepare + ANSI_RESET;
    }

    private boolean matchLabel(String label) {
        if (label.matches("[A-z\\s]+")) {
            return true;
        }

        return false;
    }
}
