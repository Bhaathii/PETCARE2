package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    private ListView cartListView;
    private Button buyNowButton;
    private ArrayList<Product> cartItems;
    private ProductAdapter adapter;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        cartListView = findViewById(R.id.cartListView);
        buyNowButton = findViewById(R.id.buyNowButton);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        // Initialize cart items
        cartItems = new ArrayList<>();

        // Get the product from the intent
        Product product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            cartItems.add(product);
            Toast.makeText(this, "Product added: " + product.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Product not received!", Toast.LENGTH_SHORT).show();
        }

        // Use the custom ProductAdapter
        adapter = new ProductAdapter(this, cartItems);
        cartListView.setAdapter(adapter);

        // Update the total price
        updateTotalPrice();

        // Handle Buy Now button click
        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItems.isEmpty()) {
                    Toast.makeText(ShoppingCartActivity.this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ShoppingCartActivity.this, PaymentActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Handle item long click to remove from cart
        cartListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cartItems.remove(position);
                adapter.notifyDataSetChanged();
                updateTotalPrice();
                Toast.makeText(ShoppingCartActivity.this, "Item removed from cart", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    // Method to calculate and update the total price
    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartItems) {
            totalPrice += product.getPrice();
        }
        totalPriceTextView.setText("Total: Rs. " + totalPrice);
    }
}
