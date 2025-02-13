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

public class ViewQuotesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<Quote, QuoteViewHolder> adapter;
    private TextView noDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quotes);

        recyclerView = findViewById(R.id.recyclerView);
        noDataTextView = findViewById(R.id.noDataTextView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference("quotes");

        FirebaseRecyclerOptions<Quote> options = new FirebaseRecyclerOptions.Builder<Quote>()
                .setQuery(databaseReference, Quote.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Quote, QuoteViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull QuoteViewHolder holder, int position, @NonNull Quote model) {
                holder.quotesTv.setText(model.getQuote());
                holder.authorTv.setText(model.getAuthor());

                // Update quote on click
                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(ViewQuotesActivity.this, UpdateQuoteActivity.class);
                    intent.putExtra("quoteKey", model.getKey());
                    intent.putExtra("quoteText", model.getQuote());
                    intent.putExtra("authorText", model.getAuthor());
                    startActivity(intent);
                });

                // Delete quote on long click
                holder.itemView.setOnLongClickListener(v -> {
                    databaseReference.child(model.getKey()).removeValue().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ViewQuotesActivity.this, "Quote Deleted!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewQuotesActivity.this, "Failed to Delete Quote", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return true;
                });
            }

            @NonNull
            @Override
            public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = getLayoutInflater().inflate(R.layout.quote_item, parent, false);
                return new QuoteViewHolder(view);
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