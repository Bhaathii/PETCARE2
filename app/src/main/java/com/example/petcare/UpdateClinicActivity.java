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

public class UpdateClinicActivity extends AppCompatActivity {

    private EditText updateClinicNameEditText, updateContactNumberEditText, updateAddressEditText, updateCityEditText, updateDescriptionEditText, updateOwnerNameEditText;
    private Button updateClinicButton;
    private DatabaseReference databaseReference;
    private String clinicKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_clinic);

        // Bind views
        updateClinicNameEditText = findViewById(R.id.updateClinicNameEditText);
        updateContactNumberEditText = findViewById(R.id.updateContactNumberEditText);
        updateAddressEditText = findViewById(R.id.updateAddressEditText);
        updateCityEditText = findViewById(R.id.updateCityEditText);
        updateDescriptionEditText = findViewById(R.id.updateDescriptionEditText);
        updateOwnerNameEditText = findViewById(R.id.updateOwnerNameEditText);
        updateClinicButton = findViewById(R.id.updateClinicButton);

        // Get data from the intent
        clinicKey = getIntent().getStringExtra("clinicKey");
        String clinicName = getIntent().getStringExtra("clinicName");
        String contactNumber = getIntent().getStringExtra("contactNumber");
        String address = getIntent().getStringExtra("address");
        String city = getIntent().getStringExtra("city");
        String description = getIntent().getStringExtra("description");
        String ownerName = getIntent().getStringExtra("ownerName");

        // Set current values in the EditText fields
        updateClinicNameEditText.setText(clinicName);
        updateContactNumberEditText.setText(contactNumber);
        updateAddressEditText.setText(address);
        updateCityEditText.setText(city);
        updateDescriptionEditText.setText(description);
        updateOwnerNameEditText.setText(ownerName);

        // Initialize database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("clinics").child(clinicKey);

        // Update button click listener
        updateClinicButton.setOnClickListener(v -> updateClinic());
    }

    private void updateClinic() {
        String updatedClinicName = updateClinicNameEditText.getText().toString().trim();
        String updatedContactNumber = updateContactNumberEditText.getText().toString().trim();
        String updatedAddress = updateAddressEditText.getText().toString().trim();
        String updatedCity = updateCityEditText.getText().toString().trim();
        String updatedDescription = updateDescriptionEditText.getText().toString().trim();
        String updatedOwnerName = updateOwnerNameEditText.getText().toString().trim();

        if (updatedClinicName.isEmpty() || updatedContactNumber.isEmpty() || updatedAddress.isEmpty() || updatedCity.isEmpty() || updatedDescription.isEmpty() || updatedOwnerName.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a HashMap to update the data
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("clinicName", updatedClinicName);
        updateMap.put("contactNumber", updatedContactNumber);
        updateMap.put("address", updatedAddress);
        updateMap.put("city", updatedCity);
        updateMap.put("description", updatedDescription);
        updateMap.put("ownerName", updatedOwnerName);

        // Update the data in Firebase
        databaseReference.updateChildren(updateMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(UpdateClinicActivity.this, "Clinic Updated Successfully!", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity after successful update
            } else {
                Toast.makeText(UpdateClinicActivity.this, "Failed to Update Clinic", Toast.LENGTH_SHORT).show();
            }
        });
    }
}