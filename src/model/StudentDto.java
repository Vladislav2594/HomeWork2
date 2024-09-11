package model;

import java.util.List;
import java.util.Objects;

public class StudentDto {
    private String fullName;
    private List<String> grades;

    public StudentDto(String fullName, List<String> grades) {
        this.fullName = fullName;
        this.grades = grades;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fullName);
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "fullName='" + fullName + '\'' +
                ", grades=" + grades +
                '}';
    }
}
