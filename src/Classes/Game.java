package Classes;


public class Game {
    // tempo, score(par), equipas(par), dia, id do jogo
    public int time;
    public Pair<Integer,Integer> gameScore;
    public Pair<Integer,Integer> teamsID;
    public int day;
    public int gameID;

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
