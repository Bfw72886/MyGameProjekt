package de.bfw.mygameprojekt;

public class Player implements Comparable<Player>{
    private final String name;
    private final int level;
    private final int punkte;

    public Player(String name, int level, int punkte) {
        this.name = name;
        this.level = level;
        this.punkte = punkte;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getPunkte() {
        return punkte;
    }

    @Override
    public int compareTo(Player otherPlayer) {
        return otherPlayer.getPunkte() - punkte;
    }
}
