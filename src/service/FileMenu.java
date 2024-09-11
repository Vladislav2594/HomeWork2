package service;

import validation.FileChoiceValidation;

import java.util.Scanner;

public class FileMenu {

    private final static String fileChoice = "Нажмите 1 если хотите создать новый файл или 2 есть использовать существующий";

    private final FileChoiceValidation fileChoiceValidation = new FileChoiceValidation();
    private final FileService fileService = new FileService();

    public boolean getFileMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println(fileChoice);
        int choice = FileChoiceValidation.validationChoice(in.nextInt());
        if (choice == 1) {
            fileService.createFile();
            return false;
        }
        if (choice == 2) {
            fileService.getFile();
        }
        return true;
    }
}
