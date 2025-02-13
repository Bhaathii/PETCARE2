package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewClinicsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<Clinic, ClinicViewHolder> adapter;
    private TextView noDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clinics);

        recyclerView = findViewById(R.id.recyclerView);
        noDataTextView = findViewById(R.id.noDataTextView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference("clinics");

        FirebaseRecyclerOptions<Clinic> options = new FirebaseRecyclerOptions.Builder<Clinic>()
                .setQuery(databaseReference, Clinic.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Clinic, ClinicViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ClinicViewHolder holder, int position, @NonNull Clinic model) {
                holder.clinicNameTv.setText("Clinic Name: " + model.getClinicName());
                holder.contactNumberTv.setText("Contact Number: " + model.getContactNumber());
                holder.addressTv.setText("Address: " + model.getAddress());
                holder.cityTv.setText("City: " + model.getCity());
                holder.descriptionTv.setText("Description: " + model.getDescription());
                holder.ownerNameTv.setText("Owner Name: " + model.getOwnerName());

                // Update clinic on click
                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(ViewClinicsActivity.this, UpdateClinicActivity.class);
                    intent.putExtra("clinicKey", model.getKey());
                    intent.putExtra("clinicName", model.getClinicName());
                    intent.putExtra("contactNumber", model.getContactNumber());
                    intent.putExtra("address", model.getAddress());
                    intent.putExtra("city", model.getCity());
                    intent.putExtra("description", model.getDescription());
                    intent.putExtra("ownerName", model.getOwnerName());
                    startActivity(intent);
                });

                // Delete clinic on long click
                holder.itemView.setOnLongClickListener(v -> {
                    databaseReference.child(model.getKey()).removeValue().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ViewClinicsActivity.this, "Clinic Deleted!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewClinicsActivity.this, "Failed to Delete Clinic", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return true;
                });
            }

            @NonNull
            @Override
            public ClinicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = getLayoutInflater().inflate(R.layout.clinic_item, parent, false);
                return new ClinicViewHolder(view);
            }

            @Override
            public void onDataChanged() {
                if (getItemCount() == 0) {
                    noDataTextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    noDataTextView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}