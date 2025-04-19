package com.example.edulog.ui.people;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edulog.R;
import com.example.edulog.databinding.FragmentHomeWorkBinding;
import com.example.edulog.databinding.FragmentPeopleBinding;
import com.example.edulog.databinding.ToolbarBinding;
import com.example.edulog.ui.homework.HomeWorkViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class PeopleFragment extends Fragment {



    FragmentPeopleBinding binding;
    RecyclerView listPeople;
    PeopleViewModel peopleViewModel;
    SwipeRefreshLayout refresher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = com.example.edulog.databinding.FragmentPeopleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        assignRecycler();
        assignToolbar();

        return root;
    }

    MaterialToolbar toolbar;
    ToolbarBinding toolbarBinding;
    TextView subjectTitle;
    ImageView backImage;
    private void assignToolbar() {
        toolbarBinding = binding.customToolbar;
        toolbar = toolbarBinding.customToolbar;
        subjectTitle = toolbarBinding.subjectTitle;
        backImage = toolbarBinding.back2;
        toolbarBinding.part1.setVisibility(GONE);
        toolbarBinding.part2.setVisibility(VISIBLE);
        peopleViewModel.getSubject().observe(getViewLifecycleOwner(), subject -> {
            subjectTitle.setText(subject);
        });
        backImage.setOnClickListener(v -> {
            requireActivity().finish();
        });
    }

    private void assignRecycler() {
        refresher = binding.refresher;
        listPeople = binding.listPeople;
        PeopleAdapter adapter = new PeopleAdapter(getContext(), null);
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));

        peopleViewModel.getHomeWorks().observe(getViewLifecycleOwner(), homeWorkList -> {
            adapter.updateItems(homeWorkList);
        });
        refresher.setOnRefreshListener(() -> {
            refresher.setRefreshing(true);
            peopleViewModel.getHomeWorks().observe(getViewLifecycleOwner(), homeWorkList -> {
                adapter.updateItems(homeWorkList);
            });
            refresher.setRefreshing(false);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        peopleViewModel =
                new ViewModelProvider(requireActivity()).get(PeopleViewModel.class);
    }




}