package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    private ImageView btnn1;
    private ImageView btnn2;

    private ImageView btnn3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize Clickable Images (Replacing FloatingActionButton)
        btnn1 = view.findViewById(R.id.btnn1);
        btnn2 = view.findViewById(R.id.btnn2);
        btnn3 = view.findViewById(R.id.btnn3);


        // Set onClickListener for btnn1
        btnn1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddPetActivity.class);
            startActivity(intent);
        });

        // Set onClickListener for btnn2
        btnn2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddPetActivity2.class);
            startActivity(intent);
        });


        // Set onClickListener for btnn3
        btnn3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity3.class);
            startActivity(intent);
        });


        return view;
    }
}
