package Players;

import Auxiliar.Pair;

public class PlayerFilter {
    private Pair<Integer, Integer> ageBounds;
    private String teamName;
    private String playerName;
    private String sport;

    public PlayerFilter() {
        this.ageBounds = new Pair<>(0, 999);
        this.teamName = this.playerName = this.sport = "";
    }

    public PlayerFilter(PlayerFilter cloneFilter) {
        this.ageBounds = cloneFilter.getAgeBounds();
        this.teamName = cloneFilter.getTeamName();
        this.playerName = cloneFilter.getPlayerName();
        this.sport = cloneFilter.getSport();
    }

    public void reset() {
        this.ageBounds = new Pair<>(0, 999);
        this.teamName = this.playerName = this.sport = "";
    }
    public Pair<Integer, Integer> getAgeBounds() {
        return ageBounds;
    }

    public void setAgeBounds(Pair<Integer, Integer> ageBounds) {
        this.ageBounds = ageBounds;
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public PlayerFilter clone() {
        return new PlayerFilter(this);
    }

    public void copyTo(PlayerFilter filter) {
        filter.setTeamName(this.teamName);
        filter.setAgeBounds(this.ageBounds);
        filter.setPlayerName(this.playerName);
        filter.setSport(this.sport);
    }
}
