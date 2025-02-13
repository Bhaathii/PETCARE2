package com.example.petcare;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuoteViewHolder extends RecyclerView.ViewHolder {
    TextView quotesTv, authorTv;

    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        quotesTv = itemView.findViewById(R.id.quotes_tv);
        authorTv = itemView.findViewById(R.id.author_tv);
    }
}