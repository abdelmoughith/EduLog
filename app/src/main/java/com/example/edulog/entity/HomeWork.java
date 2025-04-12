package com.example.edulog.entity;

import java.time.LocalDateTime;

public class HomeWork {
    private String name;
    private LocalDateTime dateTime;

    public HomeWork(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
