package com.example.edulog.ui.subjectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.edulog.R;
import com.example.edulog.databinding.ActivitySubjectDetailBinding;
import com.example.edulog.ui.homework.HomeWorkViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubjectDetailActivity extends AppCompatActivity {

    private ActivitySubjectDetailBinding binding;
    // Correct way to initialize ViewModel
    private HomeWorkViewModel homeWorkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubjectDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the ViewModel
        homeWorkViewModel = new ViewModelProvider(this).get(HomeWorkViewModel.class);

        // Get data from the Intent
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String cover = intent.getStringExtra("cover");

        // Pass the data to the ViewModel
        homeWorkViewModel.setSubject(subject);
        homeWorkViewModel.setCover(cover);

        BottomNavigationView navView = binding.navView;
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_homework,
                R.id.navigation_people
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
}
