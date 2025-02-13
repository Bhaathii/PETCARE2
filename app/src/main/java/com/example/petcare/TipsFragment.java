package com.example.petcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class TipsFragment extends Fragment {

    private RecyclerView tipsRecyclerView;
    private TipsAdapter tipsAdapter;

    public TipsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // If necessary, handle initialization with passed parameters
        if (getArguments() != null) {
            String param1 = getArguments().getString("param1");
            String param2 = getArguments().getString("param2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tips, container, false);

        // Initialize RecyclerView
        tipsRecyclerView = rootView.findViewById(R.id.tipsRecyclerView);
        tipsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> petCareTips = Arrays.asList(
                "• Make sure your pet has a balanced diet.",
                "• Regular exercise is important for your pet's health.",
                "• Groom your pet regularly to maintain cleanliness.",
                "• Keep your pet hydrated with fresh water.",
                "• Regular vet visits are essential for your pet's well-being.",
                "• Provide a safe and comfortable environment for your pet.",
                "• Train your pet with positive reinforcement for good behavior.",
                "• Keep your pet's living area clean and tidy.",
                "• Ensure your pet has plenty of mental stimulation and toys.",
                "• Monitor your pet for signs of illness and take action immediately.",
                "• Provide regular dental care to maintain your pet’s oral health.",
                "• Keep your pet's nails trimmed to prevent injury.",
                "• Ensure your pet has proper identification, like a collar or microchip.",
                "• Provide regular flea and tick prevention treatments.",
                "• Keep harmful chemicals, plants, and foods out of your pet's reach.",
                "• Ensure your pet has access to shade and cool places in hot weather.",
                "• Adjust your pet’s exercise routine according to age and breed.",
                "• Respect your pet's need for rest and relaxation.",
                "• Give your pet plenty of love and attention to build trust.",
                "• Keep your pet's fur and skin healthy by using suitable grooming products."
        );

        // Set the adapter with the list of tips
        tipsAdapter = new TipsAdapter(petCareTips);
        tipsRecyclerView.setAdapter(tipsAdapter);

        return rootView;
    }

    // Optional: If you want to use the newInstance() method for creating fragments with parameters
    public static TipsFragment newInstance(String param1, String param2) {
        TipsFragment fragment = new TipsFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }
}
