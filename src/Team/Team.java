package Team;

import Players.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private String teamName; //Team name
    private int score; // Points in scoreboard per season
    private int wins; // Wins per season
    private int losses; //Losses per season
    private int ties; // Ties per season
    private ArrayList<Player> players; // Player List

    public Team (String name) {
        this.teamName = name;
        score = 0;
        wins = 0;
        losses = 0;
        ties = 0;
        players = new ArrayList<>();
    }

    public Team (Team clone) {
        this.teamName = clone.getTeamName();
        this.score = clone.getScore();
        this.wins = clone.getWins();
        this.losses = clone.getLosses();
        this.ties = clone.getTies();
        this.players = new ArrayList<Player>();
        for(Player p : clone.getPlayers())
            this.players.add(p);
    }

    public Team (String name, int score, int wins, int losses, int ties, ArrayList<Player> players) {
        this.teamName = name;
        this.score = score;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.players = players;
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
            && (!playerSearch || this.players.stream().anyMatch(p -> p.getName().contains(filter.getPlayerName()))));
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


    public ArrayList<Player> getPlayers() {
        return players;
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


    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String sport() {
        if (this.players.size() == 0)
            return "";
        return this.players.get(0).getSport();
    }

    public void addPlayer(Player p) {
        if (!p.isInTeam()) {
            this.players.add(p);
            p.addPlayerTeam(this.teamName);
        }
    }

    public void removePlayer(Player p) {
        this.players.remove(p);
        p.setInTeam(false);
    }

    public Team clone() {
        return new Team(this);
    }

    public void copyTo(Team team) {
        team.setTeamName(this.teamName);
        team.setTies(this.ties);
        team.setWins(this.wins);
        team.setScore(this.score);
        team.setLosses(this.losses);
    }

    public void movePlayer(String playerName, Team team){
        players.removeIf(player -> player.getName().equals(playerName));
    }

    public int overallAbilityTeam(){
        int i = 0;
        for (Player p : this.players) {
            i += p.overallAbility();
        }
        return i / this.players.size();
    }
}
