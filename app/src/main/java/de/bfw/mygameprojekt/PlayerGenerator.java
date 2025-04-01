package de.bfw.mygameprojekt;

import java.util.Random;

public abstract class PlayerGenerator {
    public Player getCurrentPlayer(String name) {
        return new Player(name, 10, 1000);
    }

    public Player randomPlayer() {
        Random random = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Hannah", "Isaac", "Jack",
                "Liam", "Olivia", "Noah", "Sophia", "Ethan", "Ava", "Mason", "Mia", "Lucas", "Amelia",
                "Benjamin", "Harper", "Elijah", "Evelyn", "James", "Abigail", "William", "Ella", "Alexander", "Scarlett"};

        String name = names[random.nextInt(names.length)];
        int level = random.nextInt(10) + 1; // 1 - 10
        int punkte = random.nextInt(999) + 1; // 1 - 999

        return new Player(name, level, punkte);
    }
}
