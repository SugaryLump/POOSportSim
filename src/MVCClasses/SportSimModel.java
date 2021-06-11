package MVCClasses;

import Auxiliar.Comparators.*;
import Game.Game;
import Players.*;
import Team.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SportSimModel implements Serializable {
    private TreeSet<Team> teams;
    private TeamSortMode teamSortMode;
    private TreeSet<Player> players;
    private PlayerSortMode playerSortMode;

    public SportSimModel() {
        this.teams = new TreeSet<>(new ComparatorTeamName());
        this.teamSortMode = TeamSortMode.NAME;
        this.players = new TreeSet<>(new ComparatorPlayerSport());
        this.playerSortMode = PlayerSortMode.SPORT;
    }

    public boolean addTeam (Team t) {
        return this.teams.add(t);
    }

    public void removeTeam(Team t) {
        this.teams.remove(t);
    }

    public boolean changeTeamsOrder(TeamSortMode sortMode) {
        if (this.teamSortMode != sortMode) {
            this.teamSortMode = sortMode;
            TreeSet<Team> newTeams;
            newTeams = new TreeSet<Team>(getNewTeamComparator());
            for (Team t : this.teams)
                newTeams.add(t);
            this.teams = newTeams;
            return true;
        }
        return false;
    }

    public boolean changePlayersOrder(PlayerSortMode sortMode) {
        if (this.playerSortMode != sortMode) {
            this.playerSortMode = sortMode;
            TreeSet<Player> newPlayers;
            newPlayers = new TreeSet<Player>(getNewPlayerComparator());
            for (Player p : this.players)
                newPlayers.add(p);
            this.players = newPlayers;
            return true;
        }
        return false;
    }

    public TreeSet<Team> getFilteredTeams(TeamFilter filter) {
        return this.teams.stream()
                .filter(t-> t.passesTeamFilter(filter))
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(this.teams.comparator())));
    }

    public TreeSet<Team> getTeams() {
        return teams;
    }

    public void setTeams(TreeSet<Team> teams) {
        this.teams = teams;
    }

    public TeamSortMode getTeamSortMode() {
        return teamSortMode;
    }

    public void setTeamSortMode(TeamSortMode teamSortMode) {
        this.teamSortMode = teamSortMode;
    }

    public boolean addPlayer (Player p) {
        return this.players.add(p);
    }

    public TreeSet<Player> getPlayers() {
        return players;
    }

    public void removePlayer (Player p) {
        for(Team t : this.teams)
            t.removePlayer(p);
        this.players.remove(p);
    }

    public boolean teamNameExists(String name) {
        for (Team t : this.teams) {
            if (t.getTeamName().equals(name))
                return true;
        }
        return false;
    }

    public Team getTeamByName(String name) {
        for (Team t : this.teams) {
            if (t.getTeamName().equals(name))
                return t;
        }
        return null;
    }

    public boolean playerNameExists(String name) {
        for (Player p : this.players) {
            if (p.getName().equals(name))
                return true;
        }
        return false;
    }

    public void setPlayers(TreeSet<Player> players) {
        this.players = players;
    }

    public PlayerSortMode getPlayerSortMode() {
        return playerSortMode;
    }

    public void setPlayerSortMode(PlayerSortMode playerSortMode) {
        this.playerSortMode = playerSortMode;
    }

    public Comparator<Team> getNewTeamComparator() {
        return switch(this.teamSortMode) {
            case NAME -> (new ComparatorTeamName());
            case WINS -> (new ComparatorTeamWinsFirst());
            case LOSSES -> (new ComparatorTeamLossesFirst());
            case TIES -> (new ComparatorTeamTiesFirst());
        };
    }

    public Comparator<Player> getNewPlayerComparator() {
        return switch (this.playerSortMode) {
            case NAME -> (new ComparatorPlayerName());
            case SPORT -> (new ComparatorPlayerSport());
            case TEAM -> (new ComparatorPlayerTeam());
            case AGE -> (new ComparatorPlayerAge());
            case SKILL -> (new ComparatorPlayerSkill());
        };
    }

    public void updateInfoFromGame(Game game) {
        int scoreT1 = game.getGameScore().getL();
        int scoreT2 = game.getGameScore().getR();
        Team t1 = game.getTeams().getL();
        Team t2 = game.getTeams().getR();
        if (scoreT1 > scoreT2) {
            t1.setWins(t1.getWins() + 1);
            t2.setLosses(t2.getLosses() + 1);
        } else if (scoreT2 > scoreT1) {
            t2.setWins(t2.getWins() + 1);
            t1.setLosses(t1.getLosses() + 1);
        } else {
            t1.setTies(t1.getTies() + 1);
            t2.setTies(t2.getTies() + 1);
        }
    }
}
