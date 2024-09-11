package servlet;

import controller.StudentJournalController;
import enums.ChoiceEnum;
import model.request.CreateRequest;
import model.request.DeleteRequest;
import model.request.GetRequest;
import model.request.UpdateRequest;
import validation.RequestValidation;

import java.util.Scanner;

public class StudentJournalServlet {
    private final Scanner in = new Scanner(System.in);
    private final RequestValidation requestValidation = new RequestValidation();

    private final static String FIRST_NAME = "Введите имя студента на русском языке.";
    private final static String SECOND_NAME = "Введите фамилию студента на русском языке.";
    private final static String SUR_NAME = "Введите отчество студента на русском языке.";
    private final static String GRADE = "Введите оценку студента от 1 до 5.";
    private final static String OLD_GRADE = "Введите порядковый номер оценки студента которую хотите изменить.";
    private final static String NEW_GRADE = "Введите новую оценку студента.";

    private final StudentJournalController studentJournalController = new StudentJournalController();

    public void getServlet(int choice, boolean isAppend) {

        if (choice == ChoiceEnum.ONE.getValue()) {
            System.out.println(SECOND_NAME);
            String firstName = in.nextLine();
            System.out.println(FIRST_NAME);
            String secondName = in.nextLine();
            System.out.println(SUR_NAME);
            String surName = in.nextLine();
            System.out.println(GRADE);
            String grade = in.nextLine();

            CreateRequest createRequest = new CreateRequest(getFullName(firstName, secondName, surName), grade);

            requestValidation.createRequestValidation(createRequest);

            studentJournalController.create(createRequest, isAppend);
        }

        if (choice == ChoiceEnum.TWO.getValue()) {
            System.out.println(SECOND_NAME);
            String firstName = in.nextLine();
            System.out.println(FIRST_NAME);
            String secondName = in.nextLine();
            System.out.println(SUR_NAME);
            String surName = in.nextLine();

            DeleteRequest deleteRequest = new DeleteRequest(getFullName(firstName, secondName, surName));

            requestValidation.deleteRequestValidation(deleteRequest);

            studentJournalController.delete(deleteRequest, isAppend);
        }

        if (choice == ChoiceEnum.THREE.getValue()) {
            System.out.println(SECOND_NAME);
            String firstName = in.nextLine();
            System.out.println(FIRST_NAME);
            String secondName = in.nextLine();
            System.out.println(SUR_NAME);
            String surName = in.nextLine();
            System.out.println(OLD_GRADE);
            String oldGrade = in.nextLine();
            System.out.println(NEW_GRADE);
            String newGrade = in.nextLine();

            UpdateRequest updateRequest = new UpdateRequest(getFullName(firstName, secondName, surName), oldGrade, newGrade);

            requestValidation.updateRequestValidation(updateRequest);

            studentJournalController.update(updateRequest, isAppend);
        }

        if (choice == ChoiceEnum.FOUR.getValue()) {
            studentJournalController.getAll();
        }

        if (choice == ChoiceEnum.FIVE.getValue()) {
            System.out.println(SECOND_NAME);
            String firstName = in.nextLine();
            System.out.println(FIRST_NAME);
            String secondName = in.nextLine();
            System.out.println(SUR_NAME);
            String surName = in.nextLine();

            GetRequest getRequest = new GetRequest(getFullName(firstName, secondName, surName));

            requestValidation.getRequestValidation(getRequest);

            studentJournalController.getByName(getRequest);
        }
    }

    private String getFullName(String firstName, String secondName, String surName) {
        return firstName + " " + secondName + " " + surName;
    }
}
