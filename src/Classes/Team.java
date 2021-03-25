package Classes;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName; //Team name
    private int score; // Points in scoreboard per season
    private int wins; // Wins per seasona
    private int losses; //Losses per season
    private int ties; // Ties per season
    private int teamID; // Team Id
    private int teamGlobalAbillity; // Team Global Average Abillity
    private List<Player> playerList; // Player List

    public Team (String name) {
        this.teamName = name;
        score = 0;
        wins = 0;
        losses = 0;
        ties = 0;
        teamID = 0; //MIKE, NÃO SEI COMO QUERES FAZER COM OS IDs AQUI
        teamGlobalAbillity = 0;
        playerList = new ArrayList<Player>();
    }

    public Team (Team clone) {
        this.teamName = clone.getTeamName();
        this.score = clone.getScore();
        this.wins = clone.getWins();
        this.losses = clone.getLosses();
        this.ties = clone.getTies();
        this.teamID = clone.getTeamID();
        this.teamGlobalAbillity = clone.getTeamGlobalAbillity();
        this.playerList = clone.getPlayerList();
    }
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

    public Team clone() {
        return new Team(this);
    }

    public void copyTo(Team team) {
        team.setTeamName(this.teamName);
        team.setTeamID(this.teamID);
        team.setTies(this.ties);
        team.setWins(this.wins);
        team.setScore(this.score);
        team.setLosses(this.losses);
    }
}
