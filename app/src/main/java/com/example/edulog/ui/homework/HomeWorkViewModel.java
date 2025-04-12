package com.example.edulog.ui.homework;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edulog.entity.HomeWork;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HomeWorkViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> subject = new MutableLiveData<>();
    public LiveData<String> getSubject() { return subject; }
    public void setSubject(String subject) { this.subject.setValue(subject); }

    private final MutableLiveData<String> cover = new MutableLiveData<>();
    public LiveData<String> getCover() { return cover; }
    public void setCover(String cover) { this.cover.setValue(cover); }

    private final MutableLiveData<List<HomeWork>> homeWorks = new MutableLiveData<>();

    public LiveData<List<HomeWork>> getHomeWorks() {
        List<HomeWork> homeWorkList = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ?
                List.of(
                        // Newest
                        new HomeWork("TP:14", LocalDateTime.of(2025, 4, 12, 14, 30)),
                        new HomeWork("Chapitre 13 : Networking", LocalDateTime.of(2025, 4, 10, 14, 30)),
                        new HomeWork("TP:13", LocalDateTime.of(2025, 4, 9, 14, 30)),
                        new HomeWork("Chapitre 12 : Multithreading", LocalDateTime.of(2025, 4, 8, 14, 30)),
                        new HomeWork("TP:12", LocalDateTime.of(2025, 4, 7, 14, 30)),
                        new HomeWork("Chapitre 11 : Design Patterns", LocalDateTime.of(2025, 4, 6, 14, 30)),
                        new HomeWork("TP:11", LocalDateTime.of(2025, 4, 5, 14, 30)),
                        new HomeWork("Chapitre 10 : APIs REST", LocalDateTime.of(2025, 4, 4, 14, 30)),
                        new HomeWork("TP:10", LocalDateTime.of(2025, 4, 3, 14, 30)),
                        new HomeWork("Chapitre 9 : Firebase", LocalDateTime.of(2025, 4, 2, 14, 30)),

                        // Existing ones
                        new HomeWork("TP:4", LocalDateTime.of(2025, 4, 5, 14, 30)),
                        new HomeWork("Chapitre 3 : Stockage local", LocalDateTime.of(2025, 4, 3, 14, 30)),
                        new HomeWork("TP:3", LocalDateTime.of(2025, 3, 26, 14, 30)),
                        new HomeWork("Chapitre 2 : Persistance des données", LocalDateTime.of(2025, 3, 24, 14, 30)),
                        new HomeWork("TP 1", LocalDateTime.of(2025, 3, 10, 14, 30)),
                        new HomeWork("Chapitre 1 : Interface utilisateur sous Android", LocalDateTime.of(2025, 3, 10, 14, 30)),
                        new HomeWork("Introduction", LocalDateTime.of(2025, 2, 25, 14, 30))
                )
                :
                new ArrayList<>();
        homeWorks.setValue(homeWorkList);
        return homeWorks;
    }
}