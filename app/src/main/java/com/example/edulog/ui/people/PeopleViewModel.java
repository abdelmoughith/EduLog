package com.example.edulog.ui.people;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edulog.entity.HomeWork;
import com.example.edulog.entity.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PeopleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> subject = new MutableLiveData<>();
    public LiveData<String> getSubject() { return subject; }
    public void setSubject(String subject) { this.subject.setValue(subject); }
    private final MutableLiveData<List<Person>> people = new MutableLiveData<>();
    public LiveData<List<Person>> getHomeWorks() {
        List<Person> homeWorkList;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            homeWorkList = List.of(
                    new Person("Youssef", "El Fassi"),
                    new Person("Fatima", "Alaoui"),
                    new Person("Khadija", "Benjelloun"),
                    new Person("Ahmed", "Bennis"),
                    new Person("Mohammed", "El Idrissi"),
                    new Person("Souad", "El Fassi"),
                    new Person("Hassan", "Alaoui"),
                    new Person("Nadia", "Benjelloun"),
                    new Person("Omar", "Bennis"),
                    new Person("Layla", "El Idrissi"),
                    new Person("Samira", "El Fassi"),
                    new Person("Karim", "Alaoui"),
                    new Person("Zineb", "Benjelloun"),
                    new Person("Abdel", "Bennis"),
                    new Person("Salma", "El Idrissi"),
                    new Person("Rachid", "El Fassi"),
                    new Person("Imane", "Alaoui"),
                    new Person("Tariq", "Benjelloun"),
                    new Person("Meryem", "Bennis"),
                    new Person("Nabil", "El Idrissi")
            );
        } else {
            homeWorkList = new ArrayList<>();
            homeWorkList.add(new Person("Youssef", "El Fassi"));
            homeWorkList.add(new Person("Fatima", "Alaoui"));
            homeWorkList.add(new Person("Khadija", "Benjelloun"));
            homeWorkList.add(new Person("Ahmed", "Bennis"));
            homeWorkList.add(new Person("Mohammed", "El Idrissi"));
            homeWorkList.add(new Person("Souad", "El Fassi"));
            homeWorkList.add(new Person("Hassan", "Alaoui"));
            homeWorkList.add(new Person("Nadia", "Benjelloun"));
            homeWorkList.add(new Person("Omar", "Bennis"));
            homeWorkList.add(new Person("Layla", "El Idrissi"));
            homeWorkList.add(new Person("Samira", "El Fassi"));
            homeWorkList.add(new Person("Karim", "Alaoui"));
            homeWorkList.add(new Person("Zineb", "Benjelloun"));
            homeWorkList.add(new Person("Abdel", "Bennis"));
            homeWorkList.add(new Person("Salma", "El Idrissi"));
            homeWorkList.add(new Person("Rachid", "El Fassi"));
            homeWorkList.add(new Person("Imane", "Alaoui"));
            homeWorkList.add(new Person("Tariq", "Benjelloun"));
            homeWorkList.add(new Person("Meryem", "Bennis"));
            homeWorkList.add(new Person("Nabil", "El Idrissi"));
        }

        people.setValue(homeWorkList);
        return people;
    }

}