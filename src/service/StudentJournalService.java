package service;

import model.StudentDto;
import model.request.CreateRequest;
import model.request.DeleteRequest;
import model.request.GetRequest;
import model.request.UpdateRequest;
import repository.FileRepository;

import java.util.List;

public class StudentJournalService {

    private final FileRepository fileRepository = new FileRepository();

    public void create(CreateRequest createRequest, boolean isAppend) {
        StudentDto studentDto = new StudentDto(createRequest.fullName(), List.of(createRequest.grade()));
        System.out.println(fileRepository.save(studentDto, isAppend));
        MenuService.menu();
    }

    public void delete(DeleteRequest deleteRequest, boolean isAppend) {
        StudentDto studentDto = new StudentDto(deleteRequest.fullName(), null);
        System.out.println(fileRepository.delete(studentDto, isAppend));
        MenuService.menu();
    }

    public void update(UpdateRequest updateRequest, boolean isAppend) {
        StudentDto studentDto = fileRepository.getByName(updateRequest.fullName());
        studentDto.getGrades().set(Integer.valueOf(updateRequest.oldGrade()), updateRequest.newGrade());
        System.out.println(fileRepository.save(studentDto, isAppend));
        MenuService.menu();
    }

    public void getAll() {
        List<StudentDto> studentDtos = fileRepository.getAll();
        for (StudentDto dto : studentDtos) {
            System.out.println(dto.toString());
            MenuService.menu();
        }
    }

    public void getByName(GetRequest getRequest) {
        StudentDto studentDto = fileRepository.getByName(getRequest.fullName());
        System.out.println(studentDto.toString());
        MenuService.menu();
    }
}
