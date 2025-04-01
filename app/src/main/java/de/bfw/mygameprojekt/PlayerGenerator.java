package de.bfw.mygameprojekt;

import java.util.Random;

/**
 * Generates Players to fill the highscore gridView
 */
public abstract class PlayerGenerator {

    /**
     *
     * @param name getSharedPreferences("userPrefs", MODE_PRIVATE).getString("username", "") ->
     * for current player name
     * @return Player with level 10 and points 1000
     */
    public static Player getCurrentPlayer(String name) {
        return new Player(name, 10, 1000);
    }

    /**
     *
     * @return Player with random name, 1 - 999 points and level 0 - 9 based on points
     */
    public static Player getRandomPlayer() {
        Random random = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Hannah",
                "Isaac", "Jack", "Liam", "Olivia", "Noah", "Sophia", "Ethan", "Ava", "Mason", "Mia",
                "Lucas", "Amelia", "Benjamin", "Harper", "Elijah", "Evelyn", "James", "Abigail",
                "William", "Ella", "Alexander", "Scarlett"};

        String name = names[random.nextInt(names.length)];
        int punkte = random.nextInt(999) + 1; // 1 - 999
        int level = (int) (punkte / 100); // 0 - 9

        return new Player(name, level, punkte);
    }
}
