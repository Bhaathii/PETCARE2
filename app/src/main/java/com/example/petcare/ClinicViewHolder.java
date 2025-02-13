package com.example.petcare;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ClinicViewHolder extends RecyclerView.ViewHolder {

    TextView clinicNameTv, contactNumberTv, addressTv, cityTv, descriptionTv, ownerNameTv;

    public ClinicViewHolder(View itemView) {
        super(itemView);

        clinicNameTv = itemView.findViewById(R.id.clinicNameTv);
        contactNumberTv = itemView.findViewById(R.id.contactNumberTv);
        addressTv = itemView.findViewById(R.id.addressTv);
        cityTv = itemView.findViewById(R.id.cityTv);
        descriptionTv = itemView.findViewById(R.id.descriptionTv);
        ownerNameTv = itemView.findViewById(R.id.ownerNameTv);
    }
}