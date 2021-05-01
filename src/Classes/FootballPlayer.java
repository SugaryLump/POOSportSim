package Classes;

import Classes.Player;
import java.util.Arrays;

public class FootballPlayer extends Player {
        public String position; //Player Position

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

    public FootballPlayer(String name, int age, int[] history, int[] ability, int globalAbility, String position) {
        super(name, age, history, ability, globalAbility);
        this.position = position;
    }

    public FootballPlayer(String position) {
        this.position = position;
    }

    public FootballPlayer(Player p, String position) {
        super(p);
        this.position = position;
    }

    @Override
    public String toString() {
        return "FootballPlayer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", history=" + Arrays.toString(history) +
                ", ability=" + Arrays.toString(ability) +
                ", globalAbility=" + globalAbility +
                ", position='" + position + '\'' +
                '}';
    }
}

