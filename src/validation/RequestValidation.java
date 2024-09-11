package validation;

import exception.CharacterException;
import exception.EmptyNameException;
import exception.GradeException;
import model.request.CreateRequest;
import model.request.DeleteRequest;
import model.request.GetRequest;
import model.request.UpdateRequest;

import java.util.Objects;

public class RequestValidation {

    private final static String CHARACTER_EXCEPTION = "Имя не может содержать символы и цифры";
    private final static String NULL_POINTER_EXCEPTION = "Имя не может пустным";
    private final static String GRADE_EXCEPTION = "Оценка не может быть меньше едицицы или больше пяти.";
    private final static String SPACE = " ";

    public void createRequestValidation(CreateRequest createRequest) {
        String[] names = createRequest.fullName().split(SPACE);
        nameValidation(names[0]);
        nameValidation(names[1]);
        nameValidation(names[2]);
        gradeValidation(createRequest.grade());
    }

    public void deleteRequestValidation(DeleteRequest deleteRequest) {
        String[] names = deleteRequest.fullName().split(SPACE);
        nameValidation(names[0]);
        nameValidation(names[1]);
        nameValidation(names[2]);
    }

    public void getRequestValidation(GetRequest getRequest) {
        String[] names = getRequest.fullName().split(SPACE);
        nameValidation(names[0]);
        nameValidation(names[1]);
        nameValidation(names[2]);
    }

    public void updateRequestValidation(UpdateRequest updateRequest) {
        String[] names = updateRequest.fullName().split(SPACE);
        nameValidation(names[0]);
        nameValidation(names[1]);
        nameValidation(names[2]);
        gradeValidation(updateRequest.newGrade());
    }

    private void nameValidation(String name) {
        if (Objects.isNull(name)) {
            throw new EmptyNameException(NULL_POINTER_EXCEPTION);
        }
        for(int i = 0; i < name.length(); i++) {
            if(!Character.UnicodeBlock.of(name.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                throw new CharacterException(CHARACTER_EXCEPTION);
            }
        }
    }

    private void gradeValidation(String grade) {
        if (Integer.valueOf(grade) > 5 || Integer.valueOf(grade) < 1) {
            throw new GradeException(GRADE_EXCEPTION);
        }
    }
}
