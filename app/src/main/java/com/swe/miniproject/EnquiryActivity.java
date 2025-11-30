package com.swe.miniproject;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EnquiryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);

        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etMessage = findViewById(R.id.etMessage);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Change to ImageView
        ImageView btnBack = findViewById(R.id.btnBackEnquiry);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnSubmit.setOnClickListener(v -> {
            // For demo, just show a toast
            Toast.makeText(this, "Enquiry submitted!", Toast.LENGTH_LONG).show();
            etName.setText("");
            etEmail.setText("");
            etMessage.setText("");
        });
    }
}