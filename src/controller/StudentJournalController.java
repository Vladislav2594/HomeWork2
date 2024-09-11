package controller;

import model.request.CreateRequest;
import model.request.DeleteRequest;
import model.request.GetRequest;
import model.request.UpdateRequest;
import service.MenuService;
import service.StudentJournalService;

public class StudentJournalController {

    private final StudentJournalService service = new StudentJournalService();

    public void create(CreateRequest createRequest, boolean isAppend) {
        service.create(createRequest, isAppend);
    }

    public void delete(DeleteRequest deleteRequest, boolean isAppend) {
        service.delete(deleteRequest, isAppend);
    }

    public void update(UpdateRequest updateRequest, boolean isAppend) {
        service.update(updateRequest, isAppend);
    }

    public void getAll() {
        service.getAll();
    }

    public void getByName(GetRequest getRequest) {
        service.getByName(getRequest);
    }
}
