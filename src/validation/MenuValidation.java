package validation;

import exception.MenuException;

public class MenuValidation {

    private static final String CHOICE_INVALID = "Вы ввели значение меньше единицы или больше пяти.";

    public static int validationChoice(int choice) {
        if (choice < 1 || choice > 6) {
            throw new MenuException(CHOICE_INVALID);
        }
        return choice;
    }
}
