package com.example.edulog;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.edulog.databinding.ActivityMainBinding;
import com.example.edulog.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_account
        )
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        /*
        navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_add_post) {
                // Start the New Activity
                Intent intent = new Intent(MainActivity.this, CreatePostActivity.class);
                startActivity(intent);
                return false; // Return false to prevent fragment change
            }
            return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.fragment));
        });
         */
        //startActivity(new Intent(this, LoginActivity.class));
    }
}