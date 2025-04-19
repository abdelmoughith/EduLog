package com.example.edulog.ui.pushhomework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.edulog.R;

public class PushHomeWorkActivity extends AppCompatActivity {

    Button btnDownload;
    TextView homeworkTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_push_home_work);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        customStatusBar();
        assignValues();
    }

    private void assignValues() {
        homeworkTitle = findViewById(R.id.homeworkTitle);
        btnDownload = findViewById(R.id.btnDownload);
        homeworkTitle.setText(getIntent().getStringExtra("title"));
        btnDownload.setOnClickListener(v -> {

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