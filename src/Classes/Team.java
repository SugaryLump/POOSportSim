package Classes;

import java.util.List;

public class Team {
    public String teamName; //Team name
    public int score; // Points in scoreboard per season
    public int wins; // Wins per seasona
    public int losses; //Losses per season
    public int ties; // Ties per season
    public int teamID; // Team Id
    public int teamGlobalAbillity; // Team Global Average Abillity
    public List<Player> playerList; // Player List

    public String getTeamName() {
        return teamName;
    }

    public int getScore() {
        return score;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public int getTeamID() {
        return teamID;
    }

    public int getTeamGlobalAbillity(){
	return teamGlobalAbillity;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public void setTeamGlobalAbillity(int teamGlobalAbillity){
	this.teamGlobalAbillity = teamGlobalAbillity;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
