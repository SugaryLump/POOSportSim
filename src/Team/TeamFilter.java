package Team;

import Auxiliar.*;


import java.util.TreeSet;

public class TeamFilter {
    private Pair<Integer, Integer> winBounds;
    private Pair<Integer, Integer> loseBounds;
    private Pair<Integer, Integer> tieBounds;
    private Pair<Integer, Integer> scoreBounds;
    private Pair<Integer, Integer> winRatio;
    private String teamName;
    private String playerName;

    public TeamFilter() {
        this.winBounds
                = this.loseBounds
                = this.tieBounds
                = new Pair<>(0, 999);
        this.winRatio = new Pair<>(0,100);
        this.teamName = this.playerName = "";
        this.scoreBounds = new Pair<>(0, 999);
    }

    public TeamFilter(TeamFilter cloneFilter) {
        //this.teamName = cloneFilter.getTeamName();
        //this.winBounds = new Pair<>(cloneFilter.getWinBounds().getL()
        //       , cloneFilter.getWinBounds().getR());
        this.winBounds = cloneFilter.getWinBounds();
        this.loseBounds = cloneFilter.getLoseBounds();
        this.tieBounds = cloneFilter.getTieBounds();
        this.scoreBounds = cloneFilter.getScoreBounds();
        this.winRatio = cloneFilter.getWinRatio();
        this.teamName = cloneFilter.getTeamName();
        this.playerName = cloneFilter.getPlayerName();

    }

    public void reset() {
        this.winBounds
                = this.loseBounds
                = this.tieBounds
                = new Pair<>(0, 999);
        this.winRatio = new Pair<>(0,100);
        this.teamName = this.playerName = "";
    }
    public Pair<Integer, Integer> getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(Pair<Integer, Integer> winRatio) {
        this.winRatio = winRatio;
    }

    public Pair<Integer, Integer> getWinBounds() {
        return winBounds;
    }

    public void setWinBounds(Pair<Integer, Integer> winBounds) {
        this.winBounds = winBounds;
    }

    public Pair<Integer, Integer> getLoseBounds() {
        return loseBounds;
    }

    public void setLoseBounds(Pair<Integer, Integer> loseBounds) {
        this.loseBounds = loseBounds;
    }

    public Pair<Integer, Integer> getTieBounds() {
        return tieBounds;
    }

    public void setTieBounds(Pair<Integer, Integer> tieBounds) {
        this.tieBounds = tieBounds;
    }

    public Pair<Integer, Integer> getScoreBounds() {
        return scoreBounds;
    }

    public void setScoreBounds(Pair<Integer, Integer> scoreBounds) {
        this.scoreBounds = scoreBounds;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public TeamFilter clone() {
        return new TeamFilter(this);
    }

    public void copyTo(TeamFilter filter) {
        filter.setTeamName(this.teamName);
        filter.setWinRatio(this.winRatio);
        filter.setWinBounds(this.winBounds);
        filter.setLoseBounds(this.loseBounds);
        filter.setTieBounds(this.tieBounds);
        filter.setPlayerName(this.playerName);
    }
}
