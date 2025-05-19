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

/**
 * Highscore Screen that shows highscores ordered from highest points to lowest
 */
public class HighscoresActivity extends AppCompatActivity implements View.OnClickListener {

    GridView gridView;
    AppCompatButton startGameButton;
    ArrayAdapter<String> adapter;
    ArrayList<Highscore> highscores;
    ArrayList<String> highScoresFlat;
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

        // database part
        try (DatabaseHelperOpen databaseHelperOpen = new DatabaseHelperOpen(this)) {
            // createDummyData();
            highscores = databaseHelperOpen.getAllHighscores();
            highscores.sort(Collections.reverseOrder());
        } catch (Exception e) {
            e.printStackTrace();
        }

        highScoresFlat = new ArrayList<>();
        // header
        highScoresFlat.add("ID");
        highScoresFlat.add("Username");
        highScoresFlat.add("Points");

        // object entries from highscores to arraylist in groups of 3
        if (highscores != null) {
            for (Highscore highscoreobject : highscores) {
                String id = String.valueOf(highscoreobject.getId());
                String username = highscoreobject.getUsername();
                String points = String.valueOf(highscoreobject.getPoints());
                highScoresFlat.add(id);
                highScoresFlat.add(username);
                highScoresFlat.add(points);
            }
        }

        // playerDataArray = getHighscoreArray(5);
        playerDataArray = highScoresFlat.toArray(new String[0]);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playerDataArray);

        gridView.setAdapter(adapter);
    }

    /**
     * Method for getting data for the gridView (3 columns) with header
     * @param howManyPlayers (user itself included)
     * @return String[] of data, grouped in sets of three values, like
     * ["nameOne", "levelOne", "pointsOne", "nameTwo", "levelTwo", "pointsTwo"]
     */
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

    private void createDummyData() {
        for (int i = 0; i < 5; i++) {
            Highscore highscore = new Highscore(i + 1, "test" + i, i * 4 * i);
            try (DatabaseHelperOpen databaseHelperOpen = new DatabaseHelperOpen(this)) {
                databaseHelperOpen.insertHighscore(highscore);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}