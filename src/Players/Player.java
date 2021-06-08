package Players;

import java.util.Arrays;
import java.util.ArrayList;

public abstract class Player {
    private String name; //Player name
    private int age; // Player age
    private ArrayList<String> history; // Players last teams (for transfers)
    private String sport; // Sport that the player plays.

    public Player(String name, int age, ArrayList<String> history, String sport) {
        this.name = name;
        this.age = age;
        this.history = history;
        this.sport = sport;
    }


    public Player() {
        this.name = "";
        this.age = 0;
        this.history = new ArrayList<String>();
        this.sport = "";
    }

    public Player(Player p) {
        this.name = p.getName();
        this.age = p.getAge();
        this.history = new ArrayList<String>(p.getHistory());
        this.sport = p.getSport();

    }

    public boolean passesPlayerFilter(PlayerFilter filter) {
        return (this.name.contains(filter.getPlayerName())
                && this.age >= filter.getAgeBounds().getL()
                && this.age <= filter.getAgeBounds().getR()
                && this.currentTeam().contains(filter.getTeamName())
                && this.sport.contains(filter.getSport()));
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getHistory() {
        return this.history;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void addPlayerTeam(String teamName){
        this.history.add(0, teamName);
    }

    public void removeNoTeam() {
        this.history.remove(0);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", history=" + this.history.toString() +
                '}';
    }


    public abstract int overallAbility();

    public String currentTeam() {
        if (history.size() == 0)
            return "";
        return this.history.get(0);
    }

    public abstract Player clone();

    public void copyTo(Player p) {
        p.setAge(this.age);
        p.setSport(this.sport);
        p.setHistory(new ArrayList<>(this.history));
        p.setName(this.name);
    }
}


