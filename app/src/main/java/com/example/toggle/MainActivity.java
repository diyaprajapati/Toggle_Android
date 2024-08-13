package com.example.toggle;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ToggleButton themeToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load the theme before setting the content view
        SharedPreferences preferences = getSharedPreferences("themePrefs", MODE_PRIVATE);
        boolean isDarkTheme = preferences.getBoolean("isDarkTheme", false);
        if (isDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeToggleButton = findViewById(R.id.themeToggleButton);
        themeToggleButton.setChecked(isDarkTheme);
        updateToggleButtonIcon(isDarkTheme);

        themeToggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save the theme preference
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isDarkTheme", isChecked);
            editor.apply();

            // Recreate the activity to apply the new theme
            recreate();
        });
    }

    private void updateToggleButtonIcon(boolean isDarkTheme) {
        if (isDarkTheme) {
            themeToggleButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_moon_foreground, 0, 0);
        } else {
            themeToggleButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sun_foreground, 0, 0);
        }
    }
}
