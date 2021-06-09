package Auxiliar.Comparators;

import Players.Player;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorPlayerAge implements Comparator<Player>, Serializable {
    public int compare (Player p1, Player p2) {
        int cmp = p1.getAge() - p2.getAge();
        if (cmp == 0) {
            cmp = p1.getName().compareTo(p2.getName());
        }
        return cmp;
    }
}
