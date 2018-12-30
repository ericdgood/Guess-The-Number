package com.numberguess.edgoo.numberguess.HighScore;

public class HighScore {

    private int highLevel;
    private String Username;

    public HighScore(){

    }

    public HighScore(int highLevel, String Username){
        this.highLevel = highLevel;
        this.Username = Username;
    }

    public int getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(int highLevel) {
        this.highLevel = highLevel;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }


}
