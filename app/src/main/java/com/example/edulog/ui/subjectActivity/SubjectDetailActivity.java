package com.example.edulog.ui.subjectActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.edulog.MainActivity;
import com.example.edulog.R;
import com.example.edulog.databinding.ActivitySubjectDetailBinding;
import com.example.edulog.ui.createHW.CreateHWActivity;
import com.example.edulog.ui.homework.HomeWorkViewModel;
import com.example.edulog.ui.people.PeopleViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubjectDetailActivity extends AppCompatActivity {

    private ActivitySubjectDetailBinding binding;
    // Correct way to initialize ViewModel
    private HomeWorkViewModel homeWorkViewModel;
    private PeopleViewModel peopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubjectDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        customStatusBar();

        // Initialize the ViewModel
        homeWorkViewModel = new ViewModelProvider(this).get(HomeWorkViewModel.class);
        peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        // Get data from the Intent
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String cover = intent.getStringExtra("cover");

        // Pass the data to the ViewModel
        homeWorkViewModel.setSubject(subject);
        homeWorkViewModel.setCover(cover);
        peopleViewModel.setSubject(subject);


        BottomNavigationView navView = binding.navView;
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_homework,
                R.id.navigation_people
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_add) {
                // Start the New Activity
                Intent intent2 = new Intent(SubjectDetailActivity.this, CreateHWActivity.class);
                startActivity(intent2);
                return false; // Return false to prevent fragment change
            }
            return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.fragment));
        });
    }
    private void customStatusBar() {
        // Set status bar background color to green
        getWindow().setStatusBarColor(
                ContextCompat.getColor(this, R.color.green)
        );

        // Make status bar icons white (dark theme style)
        View decorView = getWindow().getDecorView();
        // Clear LIGHT_STATUS_BAR to ensure icons are white
        decorView.setSystemUiVisibility(0);
    }

}
