package com.example.edulog.ui.home;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edulog.databinding.FragmentHomeBinding;
import com.example.edulog.entity.Teacher;
import com.example.edulog.ui.home.subject.FilterListenner;
import com.example.edulog.entity.Subject;
import com.example.edulog.ui.home.subject.SubjectAdapter;
import com.example.edulog.ui.home.subject.TitleAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerSubject, recyclerTitles;
    private HomeViewModel homeViewModel;
    private SubjectAdapter subjectAdapter;
    private TitleAdapter titleAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textDashboard;
        //accountViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        recyclerSubject = binding.subjectsList;
        recyclerTitles = binding.titleList;


        subjectAdapter = new SubjectAdapter(getContext(), null);
        recyclerSubject.setAdapter(subjectAdapter);
        recyclerSubject.setLayoutManager(new LinearLayoutManager(getContext()));

        homeViewModel.getSubjects().observe(getViewLifecycleOwner(), subjects -> {
            subjectAdapter.updateData(subjects);
        });



        titleAdapter = new TitleAdapter(null, -1, city -> {

        });
        recyclerTitles.setAdapter(titleAdapter);
        recyclerTitles.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));

        homeViewModel.getSubjectsTitle().observe(getViewLifecycleOwner(), strings -> {
            titleAdapter.updateItems(strings);
        });


        return root;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}