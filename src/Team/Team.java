package Team;

import Players.*;
import Auxiliar.Comparators.*;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Team {
    private String teamName; //Team name
    private int score; // Points in scoreboard per season
    private int wins; // Wins per season
    private int losses; //Losses per season
    private int ties; // Ties per season
    private int teamID; // Team Id
    private int teamGlobalAbillity; // Team Global Average Ability
    private TreeSet<Player> playerTree; // Player List

    public Team (String name) {
        this.teamName = name;
        score = 0;
        wins = 0;
        losses = 0;
        ties = 0;
        teamID = 0; //MIKE, NÃO SEI COMO QUERES FAZER COM OS IDs AQUI
        teamGlobalAbillity = 0;
        playerTree = new TreeSet<>(new ComparatorPlayerName());
    }

    public Team (Team clone) {
        this.teamName = clone.getTeamName();
        this.score = clone.getScore();
        this.wins = clone.getWins();
        this.losses = clone.getLosses();
        this.ties = clone.getTies();
        this.teamID = clone.getTeamID();
        this.teamGlobalAbillity = clone.getTeamGlobalAbillity();
        this.playerTree = clone.getPlayerTree();
    }

    public Team (String name, int score, int wins, int losses, int ties, int teamID, int teamGlobalAbillity, TreeSet<Player> players) {
        this.teamName = name;
        this.score = score;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.teamID = teamID; //MIKE, NÃO SEI COMO QUERES FAZER COM OS IDs AQUI
        this.teamGlobalAbillity = teamGlobalAbillity;
        this.playerTree = players;
    }

    public boolean passesTeamFilter (TeamFilter filter) {
        double winRatio = 0;
        if (this.wins > 0)
            winRatio = (float)this.wins / (this.wins + this.losses + this.ties) * 100;
        boolean playerSearch = filter.getPlayerName().length() > 0;
        return (this.teamName.contains(filter.getTeamName())
            && this.wins >= filter.getWinBounds().getL()
            && this.wins <= filter.getLoseBounds().getR()
            && this.losses >= filter.getLoseBounds().getL()
            && this.losses <= filter.getLoseBounds().getR()
            && this.ties >= filter.getTieBounds().getL()
            && this.ties <= filter.getTieBounds().getR()
            && winRatio >= filter.getWinRatio().getL()
            && winRatio <= filter.getWinRatio().getR()
            && (!playerSearch || this.playerTree.stream().anyMatch(p -> p.getName().contains(filter.getPlayerName()))));
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

    public TreeSet<Player> getPlayerTree() {
        return playerTree;
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

    public void setPlayerTree(TreeSet<Player> playerTree) {
        this.playerTree = playerTree;
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

    public void movePlayer(int index, Team team){
        // to be discussed
    }

    public void movePlayer(String playerName, Team team){
        playerTree.removeIf(player -> player.getName().equals(playerName));
    }

    public int overallAbilityTeam(){
        TreeSet<Player> team = this.playerTree;
        int i = 0;
        for (Player p : team) {
            i += p.overallAbility();
        }
        int r = i / team.size();
        return r;
    }
}
