package Classes;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Player {
    public String name; //Player name
    public int age; // Player age
    public int [] history; // Players last teams (for transfers)
    public int [] ability; // Player abilities
    public int globalAbility; // Player abilities average

    public Player(String name, int age, int[] history, int[] ability, int globalAbility) {
        this.name = name;
        this.age = age;
        this.history = history;
        this.ability = ability;
        this.globalAbility = globalAbility;
    }

    public Player() {
        this.name = "";
        this.age = 0;
        this.history = new int[0];
        this.ability = new int[0];
        this.globalAbility = 0;
    }

    public Player(Player p) {
        this.name = p.getName();
        this.age = p.getAge();
        this.history = p.getHistory();
        this.ability = p.getAbility();
        this.globalAbility = p.getGlobalAbillity();
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int[] getAbility() {
        return ability;
    }

    public int[] getHistory() {
        return history;
    }

    public int getGlobalAbillity(){
	return globalAbility;
    }

    public void setAbility(int[] ability) {
        this.ability = ability;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHistory(int[] history) {
        this.history = history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGlobalAbiliity(int globalAbility){
	this.globalAbility = globalAbility;
    }

    public void addTeam(int id){
        int n = this.history.length+1;
        int [] array = getHistory();
        int [] newHistory = Arrays.copyOf(array,n);
        newHistory[0] = id;
        if (n >= 0) System.arraycopy(array, 0, newHistory, 1, array.length);
        this.setHistory(newHistory);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", history=" + Arrays.toString(history) +
                ", ability=" + Arrays.toString(ability) +
                ", globalAbility=" + globalAbility +
                '}';
    }
}


