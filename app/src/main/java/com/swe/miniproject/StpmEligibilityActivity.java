package com.swe.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StpmEligibilityActivity extends AppCompatActivity {

    private LinearLayout subjectsContainer;
    private List<View> subjectViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stpm_eligibility);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        subjectsContainer = findViewById(R.id.llSubjectsContainer);
        Button btnAddSubject = findViewById(R.id.btnAddSubject);

        // Add two initial subject rows
        addSubjectRow();
        addSubjectRow();

        btnAddSubject.setOnClickListener(v -> {
            addSubjectRow();
        });

        Button btnCheckEligibility = findViewById(R.id.btnCheckEligibility);
        btnCheckEligibility.setOnClickListener(v -> {
            List<String> selectedSubjects = new ArrayList<>();
            List<String> grades = new ArrayList<>();

            String pengajianAmGrade = ((AutoCompleteTextView) findViewById(R.id.etPengajianAm)).getText().toString().trim().toUpperCase();
            if (pengajianAmGrade.isEmpty()) {
                Toast.makeText(StpmEligibilityActivity.this, "Please enter Pengajian Am grade.", Toast.LENGTH_SHORT).show();
                return;
            }
            grades.add(pengajianAmGrade);

            for (View subjectView : subjectViews) {
                AutoCompleteTextView etSubjectName = subjectView.findViewById(R.id.etSubjectName);
                AutoCompleteTextView etSubjectGrade = subjectView.findViewById(R.id.etSubjectGrade);

                String subjectName = etSubjectName.getText().toString().trim();
                String subjectGrade = etSubjectGrade.getText().toString().trim().toUpperCase();

                if (!subjectName.isEmpty() && !subjectGrade.isEmpty()) {
                    selectedSubjects.add(subjectName);
                    grades.add(subjectGrade);
                }
            }

            if (selectedSubjects.size() < 2) {
                Toast.makeText(StpmEligibilityActivity.this, "Please enter at least two other subjects.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check for duplicate subjects
            Set<String> uniqueSubjects = new HashSet<>(selectedSubjects);
            if (uniqueSubjects.size() < selectedSubjects.size()) {
                Toast.makeText(StpmEligibilityActivity.this, "Please select unique subjects.", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean allEligible = true;
            for (String grade : grades) {
                if (!isEligible(grade)) {
                    allEligible = false;
                    break;
                }
            }

            if (allEligible) {
                Intent intent = new Intent(StpmEligibilityActivity.this, UserResult.class);
                ArrayList<String> diplomaCourses = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.diploma_courses)));
                intent.putStringArrayListExtra("eligibleCourses", diplomaCourses);
                intent.putExtra("sourceActivity", "STPM");
                startActivity(intent);
            } else {
                Toast.makeText(StpmEligibilityActivity.this, "We\'re sorry, you are not eligible to apply for a diploma programme.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addSubjectRow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subjectView = inflater.inflate(R.layout.stpm_subject_item, subjectsContainer, false);

        String[] grades = getResources().getStringArray(R.array.stpm_grades_array);
        String[] subjects = getResources().getStringArray(R.array.stpm_subjects_array);

        ArrayAdapter<String> gradesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, grades);
        ArrayAdapter<String> subjectsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, subjects);

        AutoCompleteTextView etSubjectName = subjectView.findViewById(R.id.etSubjectName);
        etSubjectName.setAdapter(subjectsAdapter);
        AutoCompleteTextView etSubjectGrade = subjectView.findViewById(R.id.etSubjectGrade);
        etSubjectGrade.setAdapter(gradesAdapter);

        subjectsContainer.addView(subjectView);
        subjectViews.add(subjectView);
    }

    private boolean isEligible(String grade) {
        return grade.equals("A") || grade.equals("A-") || grade.equals("B+") || grade.equals("B") || grade.equals("B-") || grade.equals("C+") || grade.equals("C");
    }
}
