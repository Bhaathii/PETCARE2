package com.example.petcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class PaymentActivity extends AppCompatActivity {
    private TextInputEditText cardNumberInput, cardHolderInput, expiryDateInput, cvvInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardNumberInput = findViewById(R.id.cardNumberInput);
        cardHolderInput = findViewById(R.id.cardHolderInput);
        expiryDateInput = findViewById(R.id.expiryDateInput);
        cvvInput = findViewById(R.id.cvvInput);
        Button payButton = findViewById(R.id.payButton);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePaymentDetails()) {
                    Toast.makeText(PaymentActivity.this, "Payment Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PaymentActivity.this, "Please fill all fields correctly.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validatePaymentDetails() {
        String cardNumber = cardNumberInput.getText().toString().trim();
        String cardHolder = cardHolderInput.getText().toString().trim();
        String expiryDate = expiryDateInput.getText().toString().trim();
        String cvv = cvvInput.getText().toString().trim();

        return !cardNumber.isEmpty() && !cardHolder.isEmpty() && !expiryDate.isEmpty() && !cvv.isEmpty();
    }
}