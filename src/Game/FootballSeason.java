package Game;

import Team.*;

import java.util.List;

public class FootballSeason {
    public int year; // Season Year
    public int championID; // Champion ID -> 00000 until end
    public int seasonID; // Season ID
    public List<Team> teamList; // List of Teams
    public List<Game> gameList; // List of Games

    public int getYear() {
        return year;
    }

    public int getChampionID() {
        return championID;
    }

    public int getSeasonID() {
        return seasonID;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setChampionID(int championID) {
        this.championID = championID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
