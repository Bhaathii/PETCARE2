package com.example.petcare;

import android.content.Intent; // Add this import statement
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateQuoteActivity extends AppCompatActivity {

    private EditText updateQuoteEditText, updateAuthorEditText;
    private Button updateQuoteButton;
    private DatabaseReference databaseReference;
    private String quoteKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_quote);

        // Bind views
        updateQuoteEditText = findViewById(R.id.updateQuoteEditText);
        updateAuthorEditText = findViewById(R.id.updateAuthorEditText);
        updateQuoteButton = findViewById(R.id.updateQuoteButton);

        // Get data from the intent
        Intent intent = getIntent(); // This line now works because Intent is imported
        quoteKey = intent.getStringExtra("quoteKey");
        String quoteText = intent.getStringExtra("quoteText");
        String authorText = intent.getStringExtra("authorText");

        // Set current values in the EditText fields
        updateQuoteEditText.setText(quoteText);
        updateAuthorEditText.setText(authorText);

        // Initialize database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("quotes").child(quoteKey);

        // Update button click listener
        updateQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuote();
            }
        });
    }

    private void updateQuote() {
        String updatedQuote = updateQuoteEditText.getText().toString().trim();
        String updatedAuthor = updateAuthorEditText.getText().toString().trim();

        if (updatedQuote.isEmpty() || updatedAuthor.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a HashMap to update the data
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("quote", updatedQuote);
        updateMap.put("author", updatedAuthor);

        // Update the data in Firebase
        databaseReference.updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UpdateQuoteActivity.this, "Quote Updated Successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity after successful update
                } else {
                    Toast.makeText(UpdateQuoteActivity.this, "Failed to Update Quote", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}