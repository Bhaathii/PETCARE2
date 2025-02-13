package com.example.petcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicViewHolder> {

    private List<Clinic> clinicList;

    public ClinicAdapter(List<Clinic> clinicList) {
        this.clinicList = clinicList != null ? clinicList : new ArrayList<>();
    }

    @NonNull
    @Override
    public ClinicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_item, parent, false);
        return new ClinicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicViewHolder holder, int position) {
        if (clinicList != null && position < clinicList.size()) {
            Clinic clinic = clinicList.get(position);

            if (clinic != null) {
                holder.clinicNameTv.setText("Clinic Name: " + (clinic.getClinicName() != null ? clinic.getClinicName() : "N/A"));
                holder.contactNumberTv.setText("Contact Number: " + (clinic.getContactNumber() != null ? clinic.getContactNumber() : "N/A"));
                holder.addressTv.setText("Address: " + (clinic.getAddress() != null ? clinic.getAddress() : "N/A"));
                holder.cityTv.setText("City: " + (clinic.getCity() != null ? clinic.getCity() : "N/A"));
                holder.descriptionTv.setText("Description: " + (clinic.getDescription() != null ? clinic.getDescription() : "N/A"));
                holder.ownerNameTv.setText("Owner Name: " + (clinic.getOwnerName() != null ? clinic.getOwnerName() : "N/A"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return clinicList != null ? clinicList.size() : 0;
    }

    public void addClinic(Clinic clinic) {
        if (clinic != null && clinicList != null) {
            clinicList.add(clinic);
            notifyItemInserted(clinicList.size() - 1);
        }
    }

    public void removeClinic(int position) {
        if (clinicList != null && position >= 0 && position < clinicList.size()) {
            clinicList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateList(List<Clinic> newList) {
        if (newList != null) {
            clinicList.clear();
            clinicList.addAll(newList);
            notifyDataSetChanged();
        }
    }
}