package Classes;

import java.util.List;

public class Team {
    // lista de jogadores, pontuaçao vitorias, derrotas, ties, nome da equipa, teamid
    public String teamName;
    public int score;
    public int wins;
    public int losses;
    public int ties;
    public int teamID;
    public List<Player> playerList;

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

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
