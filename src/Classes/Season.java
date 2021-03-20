package Classes;

import java.util.List;

public class Season {
    // ano, campeao do ano, seasonid, lista de equipas, lista de jogos
    public int year;
    public int championID;
    public int seasonID;
    public List<Team> teamList;
    public List<Game> gameList;

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
