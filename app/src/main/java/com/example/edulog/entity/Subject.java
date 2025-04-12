package com.example.edulog.entity;

public class Subject {
    private int id;
    private String title;
    private Integer studentNumber;
    private boolean missingWork;
    private Teacher teacher;
    private String group;
    private String imageUrl;

    public Subject(int id, String title, Integer studentNumber, Boolean missingWork, String group) {
        this.id = id;
        this.title = title;
        this.studentNumber = (studentNumber!= null) ? studentNumber : 0;
        this.missingWork = (missingWork!= null) ? missingWork : false;
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Subject(int id, String title, Integer studentNumber, boolean missingWork, String semestre,Teacher teacher, String imageUrl) {
        this(id, title, studentNumber, missingWork, semestre);
        this.teacher = teacher;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public boolean isMissingWork() {
        return missingWork;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


}
