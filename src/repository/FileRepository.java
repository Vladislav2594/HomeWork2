package repository;

import model.StudentDto;
import service.FileService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileRepository {

    private final FileService fileService = new FileService();
    private File file = fileService.getFile();
    private final static String CREATE = "Success create";
    private final static String UPDATE = "Success update";
    private final static String DELETE = "Success delete";
    private final static String INDENT = "    ";
    private final static String SPACE = " ";

    public String save(StudentDto studentDto, boolean isAppend) {
        try {
            FileWriter fileWriter = null;
            if (isAppend) {
                fileWriter = new FileWriter(file, true);
            } else fileWriter = new FileWriter(file);

            fileWriter.write(studentDto.getFullName() + INDENT);
            for (String grade : studentDto.getGrades()) {
                fileWriter.write(grade + SPACE);
            }
            fileWriter.write("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return CREATE;
    }

    public String delete(StudentDto studentDto, boolean isAppend) {
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            String studentLine = studentDto.getFullName() + INDENT;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(studentLine)) {
                    found = true;
                    // Пропускаем запись, тем самым удаляя ее
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.flush();
            if (!file.delete()) {
                throw new IOException("Failed to delete original file");
            }
            if (!tempFile.renameTo(file)) {
                throw new IOException("Failed to rename temp file");
            }

            if (!found) {
                return "Student not found for deletion";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DELETE;
    }

    public List<StudentDto> getAll() {
        List<StudentDto> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(INDENT);
                if (parts.length > 1) {
                    String fullName = parts[0];
                    List<String> grades = Arrays.asList(parts).subList(1, parts.length);
                    students.add(new StudentDto(fullName, grades));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public StudentDto getByName(String fullName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(fullName + INDENT)) {
                    String[] parts = line.split(INDENT);
                    List<String> grades = Arrays.asList(parts).subList(1, parts.length);
                    return new StudentDto(fullName, grades);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String update(StudentDto studentDto, String newGrade, boolean replaceGrades) {
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            String studentLine = studentDto.getFullName() + INDENT;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(studentLine)) {
                    found = true;
                    if (replaceGrades) {
                        // Замена всех оценок на новую
                        writer.write(studentDto.getFullName() + INDENT + newGrade + "\n");
                    } else {
                        // Добавление новой оценки к уже существующим
                        String[] parts = line.split(INDENT);
                        List<String> grades = new ArrayList<>(Arrays.asList(parts).subList(1, parts.length));
                        grades.add(newGrade);
                        writer.write(studentDto.getFullName() + INDENT + String.join(SPACE, grades) + "\n");
                    }
                } else {
                    writer.write(line + "\n");
                }
            }

            if (!found) {
                if (!replaceGrades) {
                    // Если студент не найден и замены не требуется, добавляем новую запись
                    save(studentDto, true);
                } else {
                    throw new RuntimeException("Student not found for update");
                }
            }

            writer.flush();
            if (!file.delete()) {
                throw new IOException("Failed to delete original file");
            }
            if (!tempFile.renameTo(file)) {
                throw new IOException("Failed to rename temp file");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return UPDATE;
    };
}
