package de.bfw.mygameprojekt;

public class Player {
    private final String name;
    private final int Level;
    private final int Punkte;

    public Player(String name, int level, int punkte) {
        this.name = name;
        Level = level;
        Punkte = punkte;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return Level;
    }

    public int getPunkte() {
        return Punkte;
    }
}
