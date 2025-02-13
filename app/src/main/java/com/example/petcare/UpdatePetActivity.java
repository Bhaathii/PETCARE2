package com.example.petcare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdatePetActivity extends AppCompatActivity {

    private EditText updatePetNameEditText, updatePetAgeEditText, updatePetSexEditText, updatePetBreedEditText, updateMedicalDatesEditText;
    private Button updatePetButton;
    private DatabaseReference databaseReference;
    private String petKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pet);

        // Bind views
        updatePetNameEditText = findViewById(R.id.updatePetNameEditText);
        updatePetAgeEditText = findViewById(R.id.updatePetAgeEditText);
        updatePetSexEditText = findViewById(R.id.updatePetSexEditText);
        updatePetBreedEditText = findViewById(R.id.updatePetBreedEditText);
        updateMedicalDatesEditText = findViewById(R.id.updateMedicalDatesEditText);
        updatePetButton = findViewById(R.id.updatePetButton);

        // Get data from the intent
        petKey = getIntent().getStringExtra("petKey");
        String petName = getIntent().getStringExtra("petName");
        String petAge = getIntent().getStringExtra("petAge");
        String petSex = getIntent().getStringExtra("petSex");
        String petBreed = getIntent().getStringExtra("petBreed");
        String medicalDates = getIntent().getStringExtra("medicalDates");

        // Set current values in the EditText fields
        updatePetNameEditText.setText(petName);
        updatePetAgeEditText.setText(petAge);
        updatePetSexEditText.setText(petSex);
        updatePetBreedEditText.setText(petBreed);
        updateMedicalDatesEditText.setText(medicalDates);

        // Initialize database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("pets").child(petKey);

        // Update button click listener
        updatePetButton.setOnClickListener(v -> updatePet());
    }

    private void updatePet() {
        String updatedPetName = updatePetNameEditText.getText().toString().trim();
        String updatedPetAge = updatePetAgeEditText.getText().toString().trim();
        String updatedPetSex = updatePetSexEditText.getText().toString().trim();
        String updatedPetBreed = updatePetBreedEditText.getText().toString().trim();
        String updatedMedicalDates = updateMedicalDatesEditText.getText().toString().trim();

        if (updatedPetName.isEmpty() || updatedPetAge.isEmpty() || updatedPetSex.isEmpty() || updatedPetBreed.isEmpty() || updatedMedicalDates.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a HashMap to update the data
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("petName", updatedPetName);
        updateMap.put("petAge", updatedPetAge);
        updateMap.put("petSex", updatedPetSex);
        updateMap.put("petBreed", updatedPetBreed);
        updateMap.put("medicalDates", updatedMedicalDates);

        // Update the data in Firebase
        databaseReference.updateChildren(updateMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(UpdatePetActivity.this, "Pet Updated Successfully!", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity after successful update
            } else {
                Toast.makeText(UpdatePetActivity.this, "Failed to Update Pet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}