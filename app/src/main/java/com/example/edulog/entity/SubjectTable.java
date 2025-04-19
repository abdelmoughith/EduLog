package com.example.edulog.entity;

import java.util.Map;

public class SubjectTable {
    private String title;
    private Boolean isHomeWork;// Optional table title
    private Map<String, String> subjectMarks; // Key: Subject, Value: Mark

    public SubjectTable(String title, Boolean isHomeWork, Map<String, String> subjectMarks) {
        this.title = title;
        this.isHomeWork = isHomeWork;
        this.subjectMarks = subjectMarks;
    }

    public Boolean isHomeWork() {
        return isHomeWork;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, String> getSubjectMarks() {
        return subjectMarks;
    }
}
