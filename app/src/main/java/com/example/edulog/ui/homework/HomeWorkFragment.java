package com.example.edulog.ui.homework;


import static android.view.View.VISIBLE;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edulog.databinding.FragmentHomeWorkBinding;
import com.example.edulog.databinding.ToolbarBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeWorkFragment extends Fragment {
    private FragmentHomeWorkBinding binding;
    TextView subjectTextView;
    RecyclerView homeworksRecycler;
    ImageView logoImage;
    HomeWorkViewModel homeWorkViewModel;
    HomeWorkAdapter homeWorkAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ToolbarBinding toolbarBinding;
    ImageView searchIcon;
    EditText searchInput;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeWorkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        subjectTextView = binding.title;
        logoImage = binding.logoImage;
        swipeRefreshLayout = binding.main;
        assignValues();



        homeWorkViewModel.getSubject().observe(getViewLifecycleOwner(), subject -> {
            subjectTextView.setText(subject);
        });

        homeWorkViewModel.getCover().observe(getViewLifecycleOwner(), coverPath -> {
            int resId = getResources().getIdentifier(coverPath, "raw", getContext().getPackageName());
            if (resId != 0) {
                bitmap = BitmapFactory.decodeResource(getResources(), resId);
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


    MaterialToolbar toolbar;
    ImageView backImage;
    Bitmap bitmap;

    private void assignValues() {
        toolbarBinding = binding.customToolbar;
        toolbar = toolbarBinding.customToolbar;
        toolbarBinding.part1.setVisibility(VISIBLE);
        toolbarBinding.part2.setVisibility(VISIBLE);
        searchIcon = toolbarBinding.searchIcon;
        searchInput = toolbarBinding.searchInput;
        backImage = toolbarBinding.back;

        searchIcon.setOnClickListener(v -> {
            if (searchInput.getVisibility() == View.INVISIBLE) {
                searchInput.setVisibility(VISIBLE);
                searchInput.requestFocus();
                // Optionally open keyboard:
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchInput, InputMethodManager.SHOW_IMPLICIT);
            } else {
                searchInput.setVisibility(View.INVISIBLE);
                // Optionally hide keyboard:
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchInput.getWindowToken(), 0);
            }
        });
        backImage.setOnClickListener(v -> {
            requireActivity().finish();
        });


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFabMenu();
    }

    private boolean isFabMenuOpen = false;
    private void setupFabMenu() {
        FloatingActionButton fabMain = binding.fabMain;
        FloatingActionButton fab1 = binding.fabOption1;
        FloatingActionButton fab2 = binding.fabOption2;
        FloatingActionButton fab3 = binding.fabOption3;

        fabMain.setOnClickListener(v -> {
            if (!isFabMenuOpen) {
                showFabMenu(fab1, fab2, fab3);
            } else {
                closeFabMenu(fab1, fab2, fab3);
            }
            isFabMenuOpen = !isFabMenuOpen;
        });
    }

    private void showFabMenu(FloatingActionButton... fabs) {
        for (FloatingActionButton fab : fabs) {
            fab.setVisibility(View.VISIBLE);
            fab.setAlpha(0f);
            fab.setScaleX(0f);
            fab.setScaleY(0f);
            fab.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(200)
                    .start();
        }
    }

    private void closeFabMenu(FloatingActionButton... fabs) {
        for (FloatingActionButton fab : fabs) {
            fab.animate()
                    .alpha(0f)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(200)
                    .withEndAction(() -> fab.setVisibility(View.GONE))
                    .start();
        }
    }


}