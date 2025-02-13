package com.example.petcare;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class PetViewHolder extends RecyclerView.ViewHolder {

    // Declare TextViews to hold the pet data
    TextView petNameTv, petAgeTv, petSexTv, petBreedTv, medicalDatesTv;

    // Constructor for initializing the views in each pet item
    public PetViewHolder(View itemView) {
        super(itemView);

        // Find the TextViews in the layout
        petNameTv = itemView.findViewById(R.id.petNameTv);
        petAgeTv = itemView.findViewById(R.id.petAgeTv);
        petSexTv = itemView.findViewById(R.id.petSexTv);
        petBreedTv = itemView.findViewById(R.id.petBreedTv);
        medicalDatesTv = itemView.findViewById(R.id.medicalDatesTv);
    }
}