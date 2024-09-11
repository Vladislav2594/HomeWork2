package validation;

import exception.MenuException;

public class FileChoiceValidation {

    private static final String CHOICE_INVALID = "Вы ввели значение меньше единицы или больше двух.";

    public static int validationChoice(int choice) {
        if (choice < 1 || choice > 2) {
            throw new MenuException(CHOICE_INVALID);
        }
        return choice;
    }
}
