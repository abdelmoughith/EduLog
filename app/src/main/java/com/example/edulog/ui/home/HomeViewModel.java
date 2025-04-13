package com.example.edulog.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edulog.entity.Subject;
import com.example.edulog.entity.Teacher;

import java.util.List;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Subject>> subjects = new MutableLiveData<>();
    private final MutableLiveData<List<String>> subjectsTitle = new MutableLiveData<>();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Home fragment");
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects.setValue(subjects);
    }


    public LiveData<List<Subject>> getSubjects() {
        Teacher ayoub = new Teacher("Ayoub Charef");
        Teacher chaimae = new Teacher("Chaimae Hasman");
        Teacher driss = new Teacher("Driss Essabbar");
        List<Subject> subjectList = List.of(
                new Subject(4, "BIG DATA", 31,false, "4IIR - G5", chaimae, "bigdata"),
                new Subject(5, "Génie Logiciel",  32,false, "4IIR - G5", driss, "glogiciel"),
                new Subject(6, "Entrepreneuriat", 32,false, "4IIR - G5", ayoub, "entrepreneuriat"),
                new Subject(7, "Administration Windows",  32,false, "4IIR - G5", chaimae, "windows"),
                new Subject(8, "Administration ORACLE 2", 32,false, "4IIR - G5", chaimae, "oracle"),
                new Subject(9, "Communication professionnelle 2", 32,false, "4IIR - G5", chaimae, "tec"),
                new Subject(10, "Programmation Mobile",  32,false, "4IIR - G5", ayoub, "mobile"),
                new Subject(10, "DEVOPS",  32,false, "4IIR - G5", ayoub, "devops"),
                new Subject(10, "Sécurité des Réseaux",  32,false, "4IIR - G5", ayoub, "securite"),
                new Subject(10, "Virtualisation",  32,false, "4IIR - G5", ayoub, "virtualisation"),
                new Subject(10, "Datawarehouse",  32,false, "4IIR - G5", chaimae, "datawh"),
                new Subject(10, "J2EE1(Strust, Hibernate)",  32,false, "4IIR - G5", chaimae, "jee"),
                new Subject(1, "Anglais 8", 1,false, "4IIR - G5", ayoub, "anglais"),
                new Subject(2, "Management", 32,true, "4IIR - G5", chaimae, "management"),
                new Subject(3, "PFA", 32,false, "4IIR - G5", ayoub, "pfa")
        );
        subjects.setValue(subjectList);
        return subjects;
    }
    public LiveData<List<String>> getSubjectsTitle() {
        List<String> strings = List.of(
                "4IIR",
                "3IIR",
                "2AP",
                "1AP"
        );
        subjectsTitle.setValue(strings);
        return subjectsTitle;
    }
}