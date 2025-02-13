package com.example.petcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    private LinearLayout accountSetting;
    private LinearLayout themesSetting;
    private LinearLayout languageSetting;
    private LinearLayout aboutSetting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("SettingFragment", "SettingFragment loaded"); // Debugging log
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Initialize views
        accountSetting = view.findViewById(R.id.accountSetting);
        themesSetting = view.findViewById(R.id.themesSetting);
        languageSetting = view.findViewById(R.id.languageSetting);
        aboutSetting = view.findViewById(R.id.aboutSetting);

        // Reference the TextView to display setting details
        TextView settingDescription = view.findViewById(R.id.settingDescription);

        // Set click listeners for each setting tab
        accountSetting.setOnClickListener(v -> {
            showLogoutDialog();
        });

        themesSetting.setOnClickListener(v -> {
            settingDescription.setText("Customize your app's appearance. Enable dynamic colors or choose a theme.");
        });

        languageSetting.setOnClickListener(v -> {
            settingDescription.setText("Change the app language to your preferred one.");
        });

        aboutSetting.setOnClickListener(v -> {
            settingDescription.setText("Version 1.0.0\nLicense: Open Source\nDeveloped by Nuwan Bhathiya.");
        });

        return view;
    }

    private void showLogoutDialog() {
        // Create an AlertDialog
        new AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout", (dialog, which) -> {
                    // Handle logout
                    logoutUser();
                })
                .setNegativeButton("Cancel", null) // Do nothing on cancel
                .setIcon(R.drawable.ic_logout) // Add a logout icon (if available)
                .show();
    }

    private void logoutUser() {
        // Clear user session or authentication data
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Navigate to the Login activity
        Intent intent = new Intent(requireContext(), login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear the back stack
        startActivity(intent);

        // Optional: Show a toast message
        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
    }
}