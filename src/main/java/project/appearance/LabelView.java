package project.appearance;

import project.controllers.LabelController;
import project.entities.Label;

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
        System.out.print("Введите запись для редактирования: ");
        String searchName = sc.nextLine();
        System.out.print("Запись для редактирования: ");
        Label search = getLabel(searchName);
        toString(search);
        System.out.print("Введите новое значение: ");
        String updateLabelName = sc.nextLine();
        System.out.print(ANSI_RED + "Существующая запись будет заменена на \"" +
                updateLabelName + "\"" +
                " (Y/N): " +
                ANSI_RESET);

        String input = sc.next();
        if (input.toLowerCase().equals("y")) {
            search.setName(updateLabelName);
            Label result = labelController.update(search);
            if (result != null) {
                System.out.println(ANSI_GREEN +
                        "Запись отредактирована на: " + toString(search) +
                        ANSI_RESET);

            }
        } else System.out.println("Редактирование отменено пользователем.");

        sc.close();
    }

    public Label save() {
        Label label = labelController.save(createLabelDialog());
        if (label.getId() != null) {
            System.out.println(ANSI_GREEN + "Тег: " + toString(label) + " сохранен." + ANSI_RESET);
        } else System.err.println("Не удалось сохранить.");

        return label;
    }

    public Label createLabelDialog() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите тег: ");
        String label = sc.nextLine();
        while (!matchLabel(label)) {
            System.err.print("Вы ошиблись в написании тега, попробуйте еще раз.");
            System.out.print("Введите тег: ");
            label = sc.nextLine();
        }
        return new Label(null, label);
    }

    public String toString(Label label) {
        String prepare = String.format("id:%d name:%s", label.getId(), label.getName());

        return ANSI_GREEN + prepare + ANSI_RESET;
    }

    private boolean matchLabel(String label) {
        if (label.matches("[A-я\\s]+")) {
            return true;
        }

        return false;
    }
}
