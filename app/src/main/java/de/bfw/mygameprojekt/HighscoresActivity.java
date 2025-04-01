package de.bfw.mygameprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoresActivity extends AppCompatActivity implements View.OnClickListener {

    GridView gridView;
    AppCompatButton startGameButton;
    ArrayAdapter<String> adapter;

    String[] playerDataArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_highscores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gridView = findViewById(R.id.gridView);
        startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(this);

        playerDataArray = getHighscoreArray(5);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playerDataArray);

        gridView.setAdapter(adapter);
    }

    private String[] getHighscoreArray(int howManyPlayers) {
        ArrayList<Player> players = new ArrayList<>();

        // Add current user to players
        players.add(PlayerGenerator.getCurrentPlayer(getSharedPreferences(
                "userPrefs", MODE_PRIVATE).getString("username", ""
        )));

        for (int i = 1; i < howManyPlayers; i++) {
            players.add(PlayerGenerator.getRandomPlayer());
        }

        // Sort by points
        Collections.sort(players);

        ArrayList<String> playerData = new ArrayList<>();

        // Create headers
        playerData.add("Name");
        playerData.add("Level");
        playerData.add("Points");

        for (int i = 0; i < players.size(); i++) {
            playerData.add(players.get(i).getName());
            playerData.add(String.valueOf(players.get(i).getLevel()));
            playerData.add(String.valueOf(players.get(i).getPunkte()));
        }

        return playerData.toArray(new String[0]);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == startGameButton.getId()) {
            Intent intent = new Intent(this, StartGameActivity.class);
            startActivity(intent);
        }
    }
}