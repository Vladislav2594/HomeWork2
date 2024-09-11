package service;

import validation.MenuValidation;

import java.util.Scanner;

public class MenuService {

    private static final String WELCOME =  "     Журнал учащихся: ";
    private static final String ADD_NEW =  "1) Добавить нового ученика";
    private static final String DELETE =   "2) Удалить ученика";
    private static final String UPDATE =   "3) Обновить оценку ученика";
    private static final String GET_ALL =  "4) Просмотр оценок всех учащихся";
    private static final String GET_ONE =  "5) Просмотр оценок конкретного учащегося";
    private static final String EXIT =     "6) Выйти";
    private static final String MENU =  WELCOME + "\n" +
                                        ADD_NEW + "\n" +
                                        DELETE + "\n" +
                                        UPDATE + "\n" +
                                        GET_ALL + "\n" +
                                        GET_ONE + "\n" +
                                        EXIT;

    public static int menu() {
        System.out.println(MENU);

        Scanner in = new Scanner(System.in);
        return MenuValidation.validationChoice(in.nextInt());
    }
}
