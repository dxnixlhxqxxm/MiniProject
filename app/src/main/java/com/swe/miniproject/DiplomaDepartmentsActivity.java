package com.swe.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class DiplomaDepartmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diploma_departments);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        ListView lvDepartments = findViewById(R.id.lvDepartments);
        String[] departments = {"Electrical Engineering", "Mechanical Engineering", "Computer Information Department"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, departments);
        lvDepartments.setAdapter(adapter);

        lvDepartments.setOnItemClickListener((parent, view, position, id) -> {
            String selectedDepartment = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(DiplomaDepartmentsActivity.this, DiplomaListActivity.class);
            intent.putExtra("department", selectedDepartment);
            startActivity(intent);
        });
    }
}
