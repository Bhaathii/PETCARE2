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

public class Addpet extends AppCompatActivity {

    private EditText quoteEditText, authorEditText;
    private Button addButton, viewQuotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet3);

        // Bind views
        quoteEditText = findViewById(R.id.editText);
        authorEditText = findViewById(R.id.editText2);
        addButton = findViewById(R.id.addbutton);
        viewQuotesButton = findViewById(R.id.viewQuotesButton);

        // Add quote button listener
        addButton.setOnClickListener(v -> {
            String quote = quoteEditText.getText().toString().trim();
            String author = authorEditText.getText().toString().trim();

            if (quote.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                addQuoteToDB(quote, author);
            }
        });

        // View quotes button listener
        viewQuotesButton.setOnClickListener(v -> {
            Intent intent = new Intent(Addpet.this, ViewQuotesActivity.class);
            startActivity(intent);
        });
    }

    private void addQuoteToDB(String quote, String author) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("quotes");
        String key = databaseReference.push().getKey();

        HashMap<String, Object> quoteMap = new HashMap<>();
        quoteMap.put("key", key);
        quoteMap.put("quote", quote);
        quoteMap.put("author", author);

        if (key != null) {
            databaseReference.child(key).setValue(quoteMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Quote Added!", Toast.LENGTH_SHORT).show();
                    quoteEditText.getText().clear();
                    authorEditText.getText().clear();
                } else {
                    Toast.makeText(this, "Failed to Add Quote", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}