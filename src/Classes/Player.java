package Classes;

public class Player {
    public String name; //Player name
    public int age; // Player age
    public int position; // Player Pos (if 0 then goalkeeper)
    public int [] history; // Players last teams (for transfers)
    public int [] abiliity; // Player abillities [speed, stamina, dexterity, agillity, head game, kick, pass abillity]
    public int globalAbility; // Player abillities average

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int[] getAbiliity() {
        return abiliity;
    }

    public int[] getHistory() {
        return history;
    }

    public int getPosition() {
        return position;
    }

    public int getGlobalAbillity(){
	return globalAbility;
    }

    public void setAbiliity(int[] abiliity) {
        this.abiliity = abiliity;
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

    public void setPosition(int position) {
        this.position = position;
    }

    public void setGlobalAbiliity(int globalAbility){
	this.globalAbility = globalAbility;
    }

}


