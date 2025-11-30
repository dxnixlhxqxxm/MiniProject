package com.swe.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QualificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        Button btnSpm = findViewById(R.id.btnSpm);
        btnSpm.setOnClickListener(v -> {
            Intent intent = new Intent(QualificationActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnStpm = findViewById(R.id.btnStpm);
        btnStpm.setOnClickListener(v -> {
            Intent intent = new Intent(QualificationActivity.this, StpmEligibilityActivity.class);
            startActivity(intent);
        });

        Button btnStam = findViewById(R.id.btnStam);
        btnStam.setOnClickListener(v -> {
            Toast.makeText(QualificationActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
        });
    }
}
