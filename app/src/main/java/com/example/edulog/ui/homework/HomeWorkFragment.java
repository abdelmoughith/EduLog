package com.example.edulog.ui.homework;


import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edulog.databinding.FragmentHomeWorkBinding;

public class HomeWorkFragment extends Fragment {
    private FragmentHomeWorkBinding binding;
    TextView subjectTextView;
    RecyclerView homeworksRecycler;
    ImageView logoImage;
    HomeWorkViewModel homeWorkViewModel;
    HomeWorkAdapter homeWorkAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeWorkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        subjectTextView = binding.title;
        logoImage = binding.logoImage;
        swipeRefreshLayout = binding.main;

        homeWorkViewModel.getSubject().observe(getViewLifecycleOwner(), subject -> {
            subjectTextView.setText(subject);
        });

        homeWorkViewModel.getCover().observe(getViewLifecycleOwner(), coverPath -> {
            int resId = getResources().getIdentifier(coverPath, "raw", getContext().getPackageName());
            if (resId != 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
                logoImage.setImageBitmap(bitmap);
            } else {
                Log.e("HomeWorkFragment", "Image resource not found: " + coverPath);
            }
        });



        // Setup RecyclerView for homework (example)
        homeworksRecycler = binding.homeworks;
        homeWorkAdapter = new HomeWorkAdapter(getContext(), null);
        homeworksRecycler.setAdapter(homeWorkAdapter);
        homeworksRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));

        homeWorkViewModel.getHomeWorks().observe(getViewLifecycleOwner(), homeWorkList -> {
            homeWorkAdapter.updateItems(homeWorkList);
        });
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            homeWorkViewModel.getHomeWorks().observe(getViewLifecycleOwner(), homeWorkList -> {
                homeWorkAdapter.updateItems(homeWorkList);
            });
            swipeRefreshLayout.setRefreshing(false);
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeWorkViewModel =
                new ViewModelProvider(requireActivity()).get(HomeWorkViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}