package de.bfw.mygameprojekt;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class HighscoresActivity extends AppCompatActivity {

    GridView gridView;
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
}