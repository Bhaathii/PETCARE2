package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private ListView productListView;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        productListView = findViewById(R.id.productListView);

        // Initialize products
        products = new ArrayList<>();
        products.add(new Product("Large Leather Collar with Spikes", "A quality leather collar made from leather and aluminum, imported from Germany.", 1200, R.drawable.collar));
        products.add(new Product("Dog Leash", "Durable and comfortable leash for your dog.", 800, R.drawable.leash));
        products.add(new Product("Dog Bowl", "Stainless steel dog bowl for food and water.", 500, R.drawable.bowl));
        products.add(new Product("Dog Bed", "Soft and cozy bed for your dog.", 2000, R.drawable.bed));
        products.add(new Product("Dog Toy", "Durable rubber toy for your dog to play with.", 300, R.drawable.toys));
        products.add(new Product("Chew Bone", "Tasty and healthy chew bone for your dog's dental care.", 450, R.drawable.bone1));
        products.add(new Product("Pet Grooming Brush", "Soft bristle brush for gentle pet grooming.", 700, R.drawable.brush));
        products.add(new Product("Pet Carrier Bag", "Comfortable and breathable carrier for easy pet travel.", 2500, R.drawable.bag));


        // Use the custom ProductAdapter
        ProductAdapter adapter = new ProductAdapter(this, products);
        productListView.setAdapter(adapter);

        // Handle item click to add to cart
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = products.get(position);
                Intent intent = new Intent(MainActivity3.this, ShoppingCartActivity.class);
                intent.putExtra("product", selectedProduct); // Pass the Product object
                startActivity(intent);
            }



        });
    }
}