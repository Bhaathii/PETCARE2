package com.example.petcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetViewHolder> {

    private List<Pet> petList;

    // Constructor for initializing the petList
    public PetAdapter(List<Pet> petList) {
        this.petList = petList != null ? petList : new ArrayList<>(); // Handle null case
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the view for the pet item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        // Ensure the position is valid and the list is not empty
        if (petList != null && position < petList.size()) {
            Pet pet = petList.get(position);

            // Safely set values to the views in the ViewHolder
            if (pet != null) {
                holder.petNameTv.setText("Name: " + (pet.getPetName() != null ? pet.getPetName() : "N/A"));
                holder.petAgeTv.setText("Age: " + (pet.getPetAge() != null ? pet.getPetAge() : "N/A"));
                holder.petSexTv.setText("Sex: " + (pet.getPetSex() != null ? pet.getPetSex() : "N/A"));
                holder.petBreedTv.setText("Breed: " + (pet.getPetBreed() != null ? pet.getPetBreed() : "N/A"));
                holder.medicalDatesTv.setText("Medical Dates: " + (pet.getMedicalDates() != null ? pet.getMedicalDates() : "N/A"));
            }
        }
    }

    @Override
    public int getItemCount() {
        // Return the correct size of the petList or 0 if it's null
        return petList != null ? petList.size() : 0;
    }

    // Method to add a new pet to the list and notify the adapter
    public void addPet(Pet pet) {
        if (pet != null && petList != null) {
            petList.add(pet);
            notifyItemInserted(petList.size() - 1); // Notify the adapter that a new item was inserted
        }
    }

    // Method to remove a pet from the list and notify the adapter
    public void removePet(int position) {
        if (petList != null && position >= 0 && position < petList.size()) {
            petList.remove(position);
            notifyItemRemoved(position); // Notify the adapter that an item was removed
        }
    }

    // Method to update the entire list
    public void updateList(List<Pet> newList) {
        if (newList != null) {
            petList.clear();
            petList.addAll(newList);
            notifyDataSetChanged(); // Notify the adapter that the data has changed
        }
    }
}