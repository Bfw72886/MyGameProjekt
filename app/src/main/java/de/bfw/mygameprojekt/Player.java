package de.bfw.mygameprojekt;

/**
 * Object that resembles a player, normally created after finishing a game
 */
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

    /**
     * Compares Players points
     * @param otherPlayer the object to be compared.
     * @return positive num if otherPlayer has more points,
     * can be used for ordering in descending order
     */
    @Override
    public int compareTo(Player otherPlayer) {
        return otherPlayer.getPunkte() - punkte;
    }
}
