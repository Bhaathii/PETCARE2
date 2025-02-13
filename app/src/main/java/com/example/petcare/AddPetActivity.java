package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddPetActivity extends AppCompatActivity {

    private EditText petNameEditText, petAgeEditText, petSexEditText, petBreedEditText, medicalDatesEditText;
    private Button addPetButton, viewPetsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet);

        // Bind views
        petNameEditText = findViewById(R.id.petNameEditText);
        petAgeEditText = findViewById(R.id.petAgeEditText);
        petSexEditText = findViewById(R.id.petSexEditText);
        petBreedEditText = findViewById(R.id.petBreedEditText);
        medicalDatesEditText = findViewById(R.id.medicalDatesEditText);
        addPetButton = findViewById(R.id.addPetButton);
        viewPetsButton = findViewById(R.id.viewPetsButton);

        // Add pet button listener
        addPetButton.setOnClickListener(v -> {
            String petName = petNameEditText.getText().toString().trim();
            String petAge = petAgeEditText.getText().toString().trim();
            String petSex = petSexEditText.getText().toString().trim();
            String petBreed = petBreedEditText.getText().toString().trim();
            String medicalDates = medicalDatesEditText.getText().toString().trim();

            if (petName.isEmpty() || petAge.isEmpty() || petSex.isEmpty() || petBreed.isEmpty() || medicalDates.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                addPetToDB(petName, petAge, petSex, petBreed, medicalDates);
            }
        });

        // View pets button listener
        viewPetsButton.setOnClickListener(v -> {
            startActivity(new Intent(AddPetActivity.this, com.example.petcare.ViewPetsActivity.class));
        });
    }

    private void addPetToDB(String petName, String petAge, String petSex, String petBreed, String medicalDates) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("pets");
        String key = databaseReference.push().getKey();

        HashMap<String, Object> petMap = new HashMap<>();
        petMap.put("key", key);
        petMap.put("petName", petName);
        petMap.put("petAge", petAge);
        petMap.put("petSex", petSex);
        petMap.put("petBreed", petBreed);
        petMap.put("medicalDates", medicalDates);

        if (key != null) {
            databaseReference.child(key).setValue(petMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Pet Added!", Toast.LENGTH_SHORT).show();
                    petNameEditText.getText().clear();
                    petAgeEditText.getText().clear();
                    petSexEditText.getText().clear();
                    petBreedEditText.getText().clear();
                    medicalDatesEditText.getText().clear();
                } else {
                    Toast.makeText(this, "Failed to Add Pet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}