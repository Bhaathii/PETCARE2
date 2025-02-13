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

public class ViewPetsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<com.example.petcare.Pet, PetViewHolder> adapter;
    private TextView noDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pets);

        recyclerView = findViewById(R.id.recyclerView);
        noDataTextView = findViewById(R.id.noDataTextView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference("pets");

        FirebaseRecyclerOptions<Pet> options = new FirebaseRecyclerOptions.Builder<Pet>()
                .setQuery(databaseReference, Pet.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Pet, PetViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PetViewHolder holder, int position, @NonNull Pet model) {
                holder.petNameTv.setText("Name: " + model.getPetName());
                holder.petAgeTv.setText("Age: " + model.getPetAge());
                holder.petSexTv.setText("Sex: " + model.getPetSex());
                holder.petBreedTv.setText("Breed: " + model.getPetBreed());
                holder.medicalDatesTv.setText("Medical Dates: " + model.getMedicalDates());

                // Update pet on click
                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(ViewPetsActivity.this, UpdatePetActivity.class);
                    intent.putExtra("petKey", model.getKey());
                    intent.putExtra("petName", model.getPetName());
                    intent.putExtra("petAge", model.getPetAge());
                    intent.putExtra("petSex", model.getPetSex());
                    intent.putExtra("petBreed", model.getPetBreed());
                    intent.putExtra("medicalDates", model.getMedicalDates());
                    startActivity(intent);
                });

                // Delete pet on long click
                holder.itemView.setOnLongClickListener(v -> {
                    databaseReference.child(model.getKey()).removeValue().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ViewPetsActivity.this, "Pet Deleted!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewPetsActivity.this, "Failed to Delete Pet", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return true;
                });
            }

            @NonNull
            @Override
            public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = getLayoutInflater().inflate(R.layout.pet_item, parent, false);
                return new PetViewHolder(view);
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