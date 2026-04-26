package org.example.manager;

import org.example.model.Student;
import org.example.util.FileHandler;
import org.example.util.Validation;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> students = new ArrayList<>();
    private List<String[]> originalRows = new ArrayList<>();
    private List<String> invalidRows = new ArrayList<>();
    private List<Integer> invalidIndexes = new ArrayList<>();

    public void loadStudents(String filePath) {
        students.clear();
        originalRows.clear();
        invalidRows.clear();
        invalidIndexes.clear();

        List<String[]> rows = FileHandler.readCSV(filePath);
        originalRows = rows;

        int index = 0;
        boolean isFirstRow = true;

        for (String[] row : rows) {
            if (isFirstRow) {
                isFirstRow = false;
                index++;
                continue;
            }
            if (row.length < 2) {
                invalidRows.add(String.join(",", row) + " -> Invalid column count");
                invalidIndexes.add(index);
                index++;
                continue;
            }
            try {
                String studentId = row[0];
                String name = row[1];

                if (!Validation.isValidStudentId(studentId)) {
                    invalidRows.add(String.join(",", row) + " -> Invalid student ID");
                    invalidIndexes.add(index);
                    index++;
                    continue;
                }
                students.add(new Student(studentId, name));
            }
            catch (Exception e) {
                invalidRows.add(String.join(",", row) + " -> Parsing error");
                invalidIndexes.add(index);
            }
            index++;
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<String[]> getOriginalRows() {
        return originalRows;
    }

    public List<String> getInvalidRows() {
        return invalidRows;
    }

    public List<Integer> getInvalidIndexes() {
        return invalidIndexes;
    }
}
