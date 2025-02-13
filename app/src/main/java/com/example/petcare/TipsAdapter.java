package com.example.petcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipViewHolder> {

    private List<String> tips;

    public TipsAdapter(List<String> tips) {
        this.tips = tips;
    }

    @Override
    public TipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the new custom item layout with CardView
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tip, parent, false);
        return new TipViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TipViewHolder holder, int position) {
        // Bind the tip text to the TextView inside CardView
        holder.tipText.setText(tips.get(position));
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public static class TipViewHolder extends RecyclerView.ViewHolder {
        public TextView tipText;

        public TipViewHolder(View view) {
            super(view);
            // Initialize the TextView for each tip
            tipText = view.findViewById(R.id.tipText);
        }
    }
}
