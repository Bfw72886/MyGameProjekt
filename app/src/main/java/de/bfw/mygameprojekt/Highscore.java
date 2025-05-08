package de.bfw.mygameprojekt;

public class Highscore {

    private final int id;
    private final String username;
    private final int points;

    public Highscore(int id, String username, int points) {
        this.id = id;
        this.username = username;
        this.points = points;
    }

    public Highscore(String username, int points) {
        this(-1 ,username, points);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }
}
