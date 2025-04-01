package de.bfw.mygameprojekt;

import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Settings Screen that lets user switch light- and darkmode
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView usernameText;
    AppCompatButton lightModeButton;
    AppCompatButton darkModeButton;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameText = findViewById(R.id.usernameText);
        sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        lightModeButton = findViewById(R.id.lightModeButton);
        darkModeButton = findViewById(R.id.darkModeButton);

        usernameText.setText(sharedPreferences.getString("username", ""));
        lightModeButton.setOnClickListener(this);
        darkModeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == lightModeButton.getId()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if (v.getId() == darkModeButton.getId()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}