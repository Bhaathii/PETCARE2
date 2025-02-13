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

public class AddPetActivity2 extends AppCompatActivity {

    private EditText clinicNameEditText, contactNumberEditText, addressEditText, cityEditText, descriptionEditText, ownerNameEditText;
    private Button addClinicButton, viewClinicsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet2);

        // Bind views
        clinicNameEditText = findViewById(R.id.clinicNameEditText);
        contactNumberEditText = findViewById(R.id.contactNumberEditText);
        addressEditText = findViewById(R.id.addressEditText);
        cityEditText = findViewById(R.id.cityEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        ownerNameEditText = findViewById(R.id.ownerNameEditText);
        addClinicButton = findViewById(R.id.addClinicButton);
        viewClinicsButton = findViewById(R.id.viewClinicsButton);

        // Add clinic button listener
        addClinicButton.setOnClickListener(v -> {
            String clinicName = clinicNameEditText.getText().toString().trim();
            String contactNumber = contactNumberEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String city = cityEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();
            String ownerName = ownerNameEditText.getText().toString().trim();

            if (clinicName.isEmpty() || contactNumber.isEmpty() || address.isEmpty() || city.isEmpty() || description.isEmpty() || ownerName.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                addClinicToDB(clinicName, contactNumber, address, city, description, ownerName);
            }
        });

        // View clinics button listener
        viewClinicsButton.setOnClickListener(v -> {
            startActivity(new Intent(AddPetActivity2.this, ViewClinicsActivity.class));
        });
    }

    private void addClinicToDB(String clinicName, String contactNumber, String address, String city, String description, String ownerName) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clinics");
        String key = databaseReference.push().getKey();

        HashMap<String, Object> clinicMap = new HashMap<>();
        clinicMap.put("key", key);
        clinicMap.put("clinicName", clinicName);
        clinicMap.put("contactNumber", contactNumber);
        clinicMap.put("address", address);
        clinicMap.put("city", city);
        clinicMap.put("description", description);
        clinicMap.put("ownerName", ownerName);

        if (key != null) {
            databaseReference.child(key).setValue(clinicMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Clinic Added!", Toast.LENGTH_SHORT).show();
                    clinicNameEditText.getText().clear();
                    contactNumberEditText.getText().clear();
                    addressEditText.getText().clear();
                    cityEditText.getText().clear();
                    descriptionEditText.getText().clear();
                    ownerNameEditText.getText().clear();
                } else {
                    Toast.makeText(this, "Failed to Add Clinic", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}