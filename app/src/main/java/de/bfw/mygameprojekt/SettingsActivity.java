package de.bfw.mygameprojekt;

import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    EditText usernameInput;
    AppCompatButton submitButton;
    AppCompatButton lightModeButton;
    AppCompatButton darkModeButton;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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

        sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        usernameInput = findViewById(R.id.usernameInput);
        submitButton = findViewById(R.id.submitButton);
        lightModeButton = findViewById(R.id.lightModeButton);
        darkModeButton = findViewById(R.id.darkModeButton);

        usernameInput.setText(sharedPreferences.getString("username", ""));
        lightModeButton.setOnClickListener(this);
        darkModeButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == lightModeButton.getId()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if (v.getId() == darkModeButton.getId()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        if (v.getId() == submitButton.getId()) {
            String newUsername = usernameInput.getText().toString();

            if (!newUsername.isEmpty()) {
                editor.putString("username", newUsername);
                editor.apply();
                Toast.makeText(this, "Benutzername geändert", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bitte Benutzername eingeben", Toast.LENGTH_SHORT).show();
            }

        }
    }
}