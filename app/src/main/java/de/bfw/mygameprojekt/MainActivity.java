package de.bfw.mygameprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Main menu Screen that lets user navigate to other screens
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton startGameButton;
    AppCompatButton highscoresButton;
    AppCompatButton settingsButton;
    AppCompatButton aboutGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        startGameButton = findViewById(R.id.startGameButton);
        highscoresButton = findViewById(R.id.highscoresButton);
        settingsButton = findViewById(R.id.settingsButton);
        aboutGameButton = findViewById(R.id.aboutGameButton);
        startGameButton.setOnClickListener(this);
        highscoresButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        aboutGameButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == startGameButton.getId()) {
            Intent intent = new Intent(this, StartGameActivity.class);
            startActivity(intent);
        }
        if (v.getId() == highscoresButton.getId()) {
            Intent intent = new Intent(this, HighscoresActivity.class);
            startActivity(intent);
        }
        if (v.getId() == settingsButton.getId()) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if (v.getId() == aboutGameButton.getId()) {
            Intent intent = new Intent(this, AboutGameActivity.class);
            startActivity(intent);
        }
    }
}