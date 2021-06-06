package Game;

import Auxiliar.*;


public class Game {
    public int time; // Time Elapsed (0-45/45-90);
    public Pair<Integer,Integer> gameScore; // Pair with the game score
    public Pair<Integer,Integer> teamsID; // Pair with teams (ID for easy access)
    public int day; // Day of game daymonth(00×00×)
    public int gameID; // Game ID

    public int getTime() {
        return time;
    }

    public Pair<Integer, Integer> getGameScore() {
        return gameScore;
    }

    public Pair<Integer, Integer> getTeamsID() {
        return teamsID;
    }

    public int getDay() {
        return day;
    }

    public int getGameID() {
        return gameID;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setGameScore(Pair<Integer, Integer> gameScore) {
        this.gameScore = gameScore;
    }

    public void setTeamsID(Pair<Integer, Integer> teamsID) {
        this.teamsID = teamsID;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
