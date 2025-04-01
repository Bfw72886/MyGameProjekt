package de.bfw.mygameprojekt;

/**
 * Object that saves current user log in data
 */
public class MyGameUser {
    private String username;
    private String password;

    public MyGameUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
